package module6;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public List<SimpleLinesMarker> routes;
	protected static PImage p;
	protected boolean visible = true;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		routes = new ArrayList<SimpleLinesMarker>();
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(1,170,201);
		pg.noStroke();
		pg.ellipse(x, y, 16, 16);

		pg.image(p, x-(float)5.5, y-(float)5.5);
		
	}

	@Override
	public void showTitle(PApplet pg, float x, float y) {
		 // show rectangle with title
		String name = getName().substring(1, getName().length()-1);
		String loc = "Loc: " + getCity().substring(1, getCity().length()-1) + ", " + 
		getCountry().substring(1, getCountry().length()-1) + " ";
		
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-8-39, Math.max(pg.textWidth(name), pg.textWidth(loc)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-8-33);
		pg.text(loc, x+3, y - 8 -18);
		
		pg.popStyle();
		// show routes
		
		
	}
	
	public String getName() {
		return getStringProperty("name");	
		
	}
	
	public String getCity()
	{
		return getStringProperty("city");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
}
