/** A class that implements a directed map graph. 
 * The graph may have self-loops, parallel edges. 
 * Vertices are integers 0 .. n-1 where each vertex 
 * corresponds to a geographic point on the map graph.
 * The edges of the graph are labeled by the road name, 
 * road type and length of the road and are
 * represented via adjacency lists.
 * 
 * @author Muaz Aljarhi
 *
 */
 package roadgraph;

import java.util.ArrayList;
import java.util.List;

class MapGraphAdjList
{
 
	private ArrayList<ArrayList<Integer>> adjListsMap; // mapping between vertices
	private  ArrayList<ArrayList<String>> adjListsLabels; // string labels of the corresponding edges (road name, road type)
	private  ArrayList<ArrayList<Double>> adjListsLengths; // lengths of the corresponding edges (of roads, in km)
	private int numEdges; // number of edges in the graph
 
	protected MapGraphAdjList()
	{
		adjListsMap = new ArrayList<>();
		adjListsLabels = new ArrayList<>();
		adjListsLengths = new ArrayList<>();
		numEdges = 0;
	}
	
	/**
	 * Report the size of vertex (road intersections) set
	 * @return The number of vertices in the graph.
	 */
	protected int getNumVertices() {
		return adjListsMap.size();
	}
	
	/**
	 * Report size of edge (road segment) set
	 * @return The number of edges in the graph.
	 */	
	protected int getNumEdges() {
		return numEdges;
	}
	
	
	/** 
	 * Gets all of the out-neighbors of a vertex.
	 * 
	 * @param v the index of vertex.
	 * @return (List<Integer>) a list of indices of vertices corresponding to the out-neighbors v.  
	 */	
	protected List<Integer> getNeighbors(int v) 
	{
		return new ArrayList<Integer>(adjListsMap.get(v));
	}
	
	/** 
	 * Gets all of the distances of the edges of a vertex with its neighbors.
	 * 
	 * @param v the index of vertex.
	 * @return (List<Integer>) a list of indices of vertices corresponding to the out-neighbors v.  
	 */	
	protected List<Double> getDistanceToNeighbors(int v) 
	{
		return new ArrayList<Double>(adjListsLengths.get(v));
	}
	
	/** 
	 * Gets all of the edge labels of a vertex with its neighbors.
	 * 
	 * @param v the index of vertex.
	 * @return (List<Integer>) a list of indices of vertices corresponding to the out-neighbors v.  
	 */	
	protected List<String> getEdgeLabels(int v) 
	{
		return new ArrayList<String>(adjListsLabels.get(v));
	}
	
	/**
	 * Add a new vertex to the graph. This vertex will
	 * have as its index the next available integer.
	 * the next available integer will also correspond
	 * to the value of this vertex
	 */
	protected void addVertex() {
		
		adjListsMap.add(new ArrayList<Integer>());
		adjListsLabels.add(new ArrayList<String>());
		adjListsLengths.add(new ArrayList<Double>());
	}
	
	
	/**
	 * Add new edge to the graph between given vertices,
	 * @param v Index of the start point of the edge to be added. 
	 * @param w Index of the end point of the edge to be added.
	 * @param label label of the edge
	 * @param length length of the edge 
	 */
	protected void addEdge(int v , int w, String label, double length) 
	{
			adjListsMap.get(v).add(w);
			adjListsLabels.get(v).add(label);
			adjListsLengths.get(v).add(length);
			numEdges++;
	}
	

}