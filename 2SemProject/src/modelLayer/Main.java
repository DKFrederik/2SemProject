package modelLayer;

import java.util.LinkedList;

//Testing crocAir
public class Main {

	public static void main(String[] args) {
        IFGraph bsi = new LinkedGraph(10);
        LinkedList<Vertex> l;
        // Constructing CrocAirlines...
          
        System.out.println("IsEmpty() (should be true): " + bsi.isEmpty());
    
        Vertex hold1 = new Vertex("Hold 1");
        Vertex hold2 = new Vertex("Hold 2");
        Vertex hold3 = new Vertex("Hold 3");
        Vertex hold4 = new Vertex("Hold 4");
        Vertex hold5 = new Vertex("Hold 5");
        Vertex hold6 = new Vertex("Hold 6");
        Vertex hold7 = new Vertex("Hold 7");
        Vertex hold8 = new Vertex("Hold 8");
        Vertex hold9 = new Vertex("Hold 9");
        Vertex hold10 = new Vertex("Hold 10");

        bsi.addVertex(hold1);
        bsi.addVertex(hold2);
        bsi.addVertex(hold3);
        bsi.addVertex(hold4);
        bsi.addVertex(hold5);
        bsi.addVertex(hold6);
        bsi.addVertex(hold7);
        bsi.addVertex(hold8);
        bsi.addVertex(hold9);
        bsi.addVertex(hold10);

        System.out.println("IsEmpty(): Should print false: " + bsi.isEmpty());

        System.out.println("Should print the number '9': " + bsi.getNoOfVertices());

        System.out.println("Testing search in the vertex list: 3 times 'true' - 1 times 'false'");
              
        System.out.println(bsi.containsVertex(hold1));
        System.out.println(bsi.containsVertex(hold2));
        System.out.println(bsi.containsVertex(hold3));
        System.out.println(bsi.containsVertex(hold4));

        bsi.addEdge(hold1, hold2);
        bsi.addEdge(hold1, hold3);
        bsi.addEdge(hold1, hold6);
        bsi.addEdge(hold1, hold7);
        bsi.addEdge(hold1, hold9);
        bsi.addEdge(hold1, hold10);
        bsi.addEdge(hold2, hold3);
        bsi.addEdge(hold3, hold4);
        bsi.addEdge(hold3, hold5);
        bsi.addEdge(hold6, hold5);
        bsi.addEdge(hold7, hold8);
        bsi.addEdge(hold8, hold9);
        bsi.addEdge(hold9, hold10);

        System.out.println("hold1 - hold2? (true): "
                + bsi.isAdjacent(hold1, hold2));

        System.out.println("hold6 - hold2? (false): "
                + bsi.isAdjacent(hold6, hold2));
        
        l = bsi.getAdjacencies(hold1);
        System.out.println("Testing adjacencies of Canberra (hold10, hold9, hold7, hold6, hold3, hold2):");
        for (Vertex v : l)
        	System.out.println(v.getName());
//
//        System.out.println("Number of edges (14): " + crocAir.getNoOfEdges());
//        System.out.println("Dfs starting in Brisbane: ");
//        crocAir.UnMark();
//        crocAir.Dfs(brisbane);
//        System.out.println("Dfs starting in Perth: ");
//        crocAir.UnMark();
//        crocAir.Dfs(perth);
//
//        //System.out.println("Bfs starting in Brisbane: ");
//        //crocAir.UnMark();
//        //crocAir.Bfs(brisbane);
//
//        crocAir.Clear();
//        System.out.println("IsEmpty(): Should print true: " + crocAir.isEmpty());
//
//// 
//
//        System.out.println("System end");

	}

}
