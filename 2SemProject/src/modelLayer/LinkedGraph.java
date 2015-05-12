package modelLayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class LinkedGraph implements IFGraph  {

	   private List<Vertex> vertices;
	   private List<LinkedList<Vertex>> adjList;
	   private int noVer;
	   private int noOfEdges;
	
	   public LinkedGraph(int noVer)
	   {
	        init(noVer);
	        noOfEdges = 0;
	   }
	
	   private void init(int noVer)
	   {
	        vertices = new ArrayList<Vertex>(noVer);
	        this.noVer = noVer - 1;
	        adjList = new ArrayList<LinkedList<Vertex>>(noVer);
	        for(int i = 0; i < noVer; i++)
	           adjList.add(new LinkedList<Vertex>());    
	    }
	   
	   public void graphColoring(){	   
		   // Assign the first color to first vertex
		   vertices.get(0).setColor(0);
		   
		   // Initialize remaining V-1 vertices as unassigned
		   for(int i = 1; i < noVer; i++){
			   vertices.get(i).setColor(-1);
		   }

		   ArrayList<Boolean> tempList = new ArrayList<Boolean>(noVer);
		   System.out.println(tempList.size());
		   //tempList.add(0, true);
		   
		   for(int i = 0; i <= noVer; i++){
			   tempList.add(i, false);
		   }
		   
		   //For each vertex
		   for(int i = 1; i <= noVer; i++){
			   
			   //For each adjacency
			   for(int j = 0; j < adjList.get(i).size(); j++){
				   
				   //If vertex of the adjacency is colored set the corresponding index in the temporary list to true
				   if(adjList.get(i).get(j).getColor() != -1){
					   tempList.set(adjList.get(i).get(j).getColor(), true);
					   }
				   }
			   
			   //Loops through the temporary list and finds the first available color and assigns it to the vertex 
			   int u = 0;
			   boolean found = false;
			   while(u <= noVer && !found){
				   if(tempList.get(u) == false){
					   vertices.get(i).setColor(u);
					   found = true;
				   }
				   u++;
			   }
			   //Resets the temporary list for the next iteration
			   for(int k = 0; k <= noVer; k++){
				   tempList.set(k, false);
			   }	   
		   }
		   //Prints the color of each vertex
		   for(int i = 0; i <= noVer; i++){
			   System.out.println("Vertex " + i + " ---> color " + vertices.get(i).getColor());
		   } 
	   }

		@Override
		public void addVertex(Vertex vertex) {
			vertices.add(vertex);
		}
		
		@Override
		public void addEdge(Vertex startVertex, Vertex endVertex) {
			int startIndex = vertices.indexOf(startVertex);
			adjList.get(startIndex).addFirst(endVertex);
			int endIndex = vertices.indexOf(endVertex);
			adjList.get(endIndex).addFirst(startVertex);
			noOfEdges++;
		}

		@Override
		public boolean containsVertex(Vertex vertex) {
			if(vertices.contains(vertex)){
				return true;
			}
			else {
				return false;
			}	
		}
		
		@Override
		public boolean isAdjacent(Vertex startVertex, Vertex endVertex) {
			LinkedList<Vertex> startAdjacencies = getAdjacencies(startVertex);
			//LinkedList<Vertex> endAdjacencies = getAdjacencies(endVertex);
			return startAdjacencies.contains(endVertex); // || endAdjacencies.contains(startVertex);
		}
		
		@Override
		public LinkedList<Vertex> getAdjacencies(Vertex vertex) {
			int index = vertices.indexOf(vertex);
			return adjList.get(index);		
		}
		
		@Override
		public boolean isEmpty() {
			return vertices.isEmpty();
		}
		
		@Override
		public int getNoOfVertices() {	
			return noVer+1;
		}
		
		@Override
		public int getNoOfEdges() {
			return noOfEdges;
		}

		@Override
		public void clear() {
			vertices.clear();
		}
	
		@Override
		public void unColor() {
			System.out.println("Unmarked complete");
			for(int i = 0; i < vertices.size(); i++)
			{
				vertices.get(i).setColor(-1);
			}
	    }
		
		public Vertex getVertex(int index)
		{
			return this.vertices.get(index);
		}

//		@Override
//		public void dfs(Vertex vertex){
//		System.out.println(vertex.getName() + " is marked");
//		vertex.setColor(1);
//		int index = vertices.indexOf(vertex);
//		for(int i = 0; i < adjList.get(index).size(); i++)
//		{
//			if(!adjList.get(index).get(i).isColor())
//			{
//				dfs(adjList.get(index).get(i));
//			}
//		}
//		}

		@Override
		public void bfs(Vertex vertex) {
			// TODO Auto-generated method stub
			
		}

@Override
public void dfs(Vertex vertex) {
	// TODO Auto-generated method stub
	
}

}
