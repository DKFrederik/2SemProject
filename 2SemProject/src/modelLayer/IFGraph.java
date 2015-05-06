package modelLayer;

import java.util.LinkedList;

public interface IFGraph {

	public abstract void addVertex(Vertex vertex);

	public abstract void addEdge(Vertex startVertex, Vertex endVertex);

	public abstract boolean containsVertex(Vertex vertex);

	public abstract boolean isAdjacent(Vertex startVertex, Vertex endVertex);

	public abstract LinkedList<Vertex> getAdjacencies(Vertex vertex);

	public abstract boolean isEmpty();

	public abstract int getNoOfVertices();

	public abstract int getNoOfEdges();

	public abstract void clear();

	public abstract void unColor();
	
	public abstract void dfs(Vertex vertex);
	
	public abstract void bfs(Vertex vertex);

}