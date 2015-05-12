package modelLayer;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Testing crocAir
public class Main {

	public static void main(String[] args) {
        IFGraph bsi = new LinkedGraph(6);
        LinkedList<Vertex> l;
          
        System.out.println("IsEmpty() (should be true): " + bsi.isEmpty());
    
        Vertex hold0 = new Vertex("Hold 0");
        Vertex hold1 = new Vertex("Hold 1");
        Vertex hold2 = new Vertex("Hold 2");
        Vertex hold3 = new Vertex("Hold 3");
        Vertex hold4 = new Vertex("Hold 4");
        Vertex hold5 = new Vertex("Hold 5");
//        Vertex hold7 = new Vertex("Hold 7");
//        Vertex hold8 = new Vertex("Hold 8");
//        Vertex hold9 = new Vertex("Hold 9");
//        Vertex hold10 = new Vertex("Hold 10");

        bsi.addVertex(hold0);
        bsi.addVertex(hold1);
        bsi.addVertex(hold2);
        bsi.addVertex(hold3);
        bsi.addVertex(hold4);
        bsi.addVertex(hold5);
//        bsi.addVertex(hold7);
//        bsi.addVertex(hold8);
//        bsi.addVertex(hold9);
//        bsi.addVertex(hold10);

        System.out.println("IsEmpty(): Should print false: " + bsi.isEmpty());

        System.out.println("Should print the number '4': " + bsi.getNoOfVertices());

        System.out.println("Testing search in the vertex list: 3 times 'true' - 1 times 'false'");
              
//        System.out.println(bsi.containsVertex(hold0));
//        System.out.println(bsi.containsVertex(hold1));
//        System.out.println(bsi.containsVertex(hold2));
//        System.out.println(bsi.containsVertex(hold3));

//        bsi.addEdge(hold0, hold1);
//        bsi.addEdge(hold0, hold2);
//        bsi.addEdge(hold0, hold3);
//        bsi.addEdge(hold0, hold4);
//        bsi.addEdge(hold1, hold0);
//        bsi.addEdge(hold1, hold2);
//        bsi.addEdge(hold2, hold0);
//        bsi.addEdge(hold2, hold1);
//        bsi.addEdge(hold2, hold3);
//        bsi.addEdge(hold3, hold0);
//        bsi.addEdge(hold3, hold2);
//        bsi.addEdge(hold3, hold4);
//        bsi.addEdge(hold4, hold0);
//        bsi.addEdge(hold4, hold3);
//        bsi.addEdge(hold5, hold0);
//        bsi.addEdge(hold0, hold5);
//        bsi.graphColoring();
        
    	List<Integer> numbers = Arrays.asList(1, 5, 5, 2, 1, 3,
    			21, 23, 7, 5, 3, 2, 4);
    	System.out.println("Filtering odds and getting them as a list: ");
        List<Integer> l = numbers.stream()
        	.filter((x) -> x % 2 != 0)
        	.collect(toList());
        for(int x : l) System.out.println(x);
        
        //Double edged colored graph
        bsi.addEdge(hold0, hold1);
        bsi.addEdge(hold0, hold2);
        bsi.addEdge(hold0, hold3);
        bsi.addEdge(hold0, hold4);
        bsi.addEdge(hold0, hold5);
        bsi.addEdge(hold1, hold2);
        bsi.addEdge(hold2, hold3);
        bsi.addEdge(hold3, hold4);
        bsi.graphColoring();
        

//        System.out.println("hold1 - hold2? (true): "
//                + bsi.isAdjacent(hold1, hold2));
//
//        System.out.println("hold6 - hold2? (false): "
//                + bsi.isAdjacent(hold6, hold2));
//        
//        l = bsi.getAdjacencies(hold1);
//        System.out.println("Testing adjacencies of Canberra (hold10, hold9, hold7, hold6, hold3, hold2):");
//        for (Vertex v : l)
//        	System.out.println(v.getName());
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
