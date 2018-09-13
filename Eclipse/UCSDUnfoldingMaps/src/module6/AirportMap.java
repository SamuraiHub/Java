package module6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
import parsing.ParseFeed;
import processing.core.PApplet;

/** An applet that shows airports (and routes)
 * on a world map.  
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMap extends PApplet {
	
	UnfoldingMap map;
	private List<Marker> airportList;
	
	private CommonMarker lastSelected;
	private CommonMarker lastClicked;
	
	public void setup() {
		// setting up PAppler
		size(900,750, OPENGL);
		
		// setting up map and default events
		map = new UnfoldingMap(this, 50, 50, 800, 650, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// get features from airport data
		List<PointFeature> features = ParseFeed.parseAirports(this, "airports.dat");
		
		// list for markers, hashmap for quicker access when matching with routes
		airportList = new ArrayList<Marker>();
		HashMap<Integer, Integer> airports = new HashMap<Integer, Integer>();
		AirportMarker.p = this.loadImage("airport.jpg");
		AirportMarker.p.resize(11, 11);
	
		// create markers from features
		for(PointFeature feature : features) {
			AirportMarker m = new AirportMarker(feature);
			
			airports.put(Integer.parseInt(feature.getId()), airportList.size());
			// put airport in hashmap with OpenFlights unique id for key
	
			m.setRadius(5);
			airportList.add(m);
			//System.out.println(m.getCountry());
		}
		
		List<Marker> routeList = new ArrayList<Marker>(3000);
		
		// parse route data
		List<ShapeFeature> routes = ParseFeed.parseRoutes(this, "routes.dat");
		for(ShapeFeature route : routes) {
			
			// get source and destination airportIds
			int source = Integer.parseInt((String)route.getProperty("source"));
			int dest = Integer.parseInt((String)route.getProperty("destination"));
			//System.out.println(route.);
			// get locations for airports on route
			if(airports.containsKey(source) && airports.containsKey(dest)) {
				route.addLocation(airportList.get(airports.get(source)).getLocation());
				route.addLocation(airportList.get(airports.get(dest)).getLocation());
			
			route.putProperty("Dest", airports.get(dest));
			
			routeList.add(new SimpleLinesMarker(route.getLocations(), route.getProperties()));
			
			SimpleLinesMarker rout = (SimpleLinesMarker) routeList.get(routeList.size()-1);
			
			((AirportMarker) airportList.get(airports.get(source))).routes.add(rout);
			
			rout.setHidden(true);
			}
			//System.out.println(sl.getProperties());
			
			//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		}
		
		
		
		//UNCOMMENT IF YOU WANT TO SEE ALL ROUTES
		map.addMarkers(routeList);
		
		map.addMarkers(airportList);
		
	}
	
	public void DisplayAll()
	{
		for(int i = 0; i < airportList.size(); i++)
		{
			((AirportMarker)airportList.get(i)).visible = true;
		}
		
		unhideMarkers();
	}
	
	public void AirportByContinent(String[] Countries)
	{
		hideMarkers();
		
		for(int i = 0; i < airportList.size(); i++)
		{
			((AirportMarker)airportList.get(i)).visible = false;
		}
		
		
		for(int i = 0; i < airportList.size(); i++)
		{
			String S = ((AirportMarker)airportList.get(i)).getCountry();
			
			int j = Arrays.binarySearch(Countries, S.substring(1, S.length()-1));
			
			if (j >= 0)
			{
				((AirportMarker)airportList.get(i)).visible = true;
				((AirportMarker)airportList.get(i)).setHidden(false);	
			}
		}	
	}
	
	public void draw() {
		background(0);
		map.draw();
		//addKey();
		
		if (lastSelected != null)
		{
			lastSelected.showTitle(this,lastSelected.getScreenPosition(map).x,lastSelected.getScreenPosition(map).y);
			
		}
	}
	
	/** Event handler that gets called automatically when the 
	 * mouse moves.
	 */
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		
		for (Marker m : airportList) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(map,  mouseX, mouseY) && !marker.isHidden()) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	
	
	@Override
	public void mouseClicked()
	{
		if (lastSelected == null) {
			unhideMarkers();
			lastClicked = null;
		}
		else
		{
			hideMarkers();
			lastClicked = lastSelected; 
			lastSelected.setHidden(false);
			
			for (Marker mhide: ((AirportMarker)lastSelected).routes)
			{
				if(((AirportMarker)airportList.get(mhide.getIntegerProperty("Dest"))).visible)
				{
				  mhide.setHidden(false);
				  airportList.get(mhide.getIntegerProperty("Dest")).setHidden(false);
				}
			}
		}
	}
	
	
	// loop over and unhide all markers
		private void unhideMarkers() {
			for(Marker marker : airportList) {
				if(((AirportMarker)marker).visible)
				marker.setHidden(false);
			}
			
			if(lastClicked != null)
			{
			   for(Marker marker: ((AirportMarker)lastClicked).routes)
			   {
				  marker.setHidden(true);
			   }
			}
		}
		
		private void hideMarkers() {
			for(Marker marker : airportList) {
				marker.setHidden(true);	
			}
			
			if(lastClicked != null)
			{
			   for(Marker marker: ((AirportMarker)lastClicked).routes)
			   {
				  marker.setHidden(true);
			   }
			}
		}
		
		/*private void addKey() {	
			// Remember you can use Processing's graphics methods here
			fill(255, 250, 240);
						
			rect(25, 50, 300, 200);
			
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(12);
			text("Airport Key", 50, 75);
			
			fill(1,170,201);
			noStroke();
			ellipse(60, 100, 16, 16);

			image(AirportMarker.p, 60-(float)5.5, 100-(float)5.5);

			fill(0, 0, 0);
			textAlign(LEFT, CENTER);
			text("Airport Marker", 75, 100);
			
			text("Move the mouse to Any airport to", 50, 130);
			text("display airport name and location", 50, 150);
			
			text("Click on any airport to display routes", 50, 180);
			text("to other airports and anywhere else to reset", 50, 200);
		}*/
}
