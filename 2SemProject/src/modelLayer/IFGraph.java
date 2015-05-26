package modelLayer;

import java.util.LinkedList;

public interface IFGraph {
	/**
	 * Add a vertex to the graph
	 * @param vertex The vertex that is to be added
	 */
	public abstract void addVertex(Vertex vertex);
	
	/**
	 * Adds an edge between 2 vertices. 
	 * @param startVertex 
	 * @param endVertex
	 */
	public abstract void addEdge(Vertex startVertex, Vertex endVertex);
	
	/**
	 * Checks if the Vertex is in the vertices list.
	 * @param vertex the vertex that is checked.
	 * @return true if contains, else false.
	 */
	public abstract boolean containsVertex(Vertex vertex);
	
	/**
	 * Checks if 2 vertices have an edge
	 * @param startVertex 
	 * @param endVertex
	 * @return true if is adjacent else false.
	 */
	public abstract boolean isAdjacent(Vertex startVertex, Vertex endVertex);

	/**
	 * Gets the list of adjacencies for a vertex.
	 * @param vertex the vertex to get the list for.
	 * @return A LinkedList of vertices adjacent to the vertex.
	 */
	public abstract LinkedList<Vertex> getAdjacencies(Vertex vertex);
	/**
	 * Checks if vertices list is empty.
	 * @return true or false.
	 */
	public abstract boolean isEmpty();
	/**
	 * Get the amount of vertices.
	 * @return an int corresponding to the amount of vertices.
	 */
	public abstract int getNoOfVertices();
	
	/**
	 * Gets the number of edges
	 * @return the amount of edges.
	 */
	public abstract int getNoOfEdges();
	/**
	 * Clear the list of all elements. 
	 */
	public abstract void clear();
	/**
	 * Sets the color to -1 from all the vertices.
	 */
	public abstract void unColor();
	/**
	 * Colors the graph using a greedy approach.
	 */
	public abstract void graphColoring();

}