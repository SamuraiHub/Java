/** A class that implements a node in a graph with vertex and distance to destination for better short path finding. **/
package roadgraph;

class Ride_Distance{
    
    protected Ride_Distance(int vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    protected  int vertex;
    protected double distance;
    
    @Override
    public String toString()
    {
     return vertex+";"+distance;   
    }
}