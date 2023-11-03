package Ejercicios__Test;

public class Grafo_Ejemplo {
public static void main(String[] args) {
		
		// Adjacency Matrix = An array to store 1's/0's to represent edges
		//				       # of rows =    # of unique nodes
		//				       # of columns = # of unique nodes
			
		//					  runtime complexity to check an Edge: O(1)
		//					  space complexity: O(v^2)
		
		Graph graph = new Graph(5);
		
		graph.addNode(new Node2('A'));
		graph.addNode(new Node2('B'));
		graph.addNode(new Node2('C'));
		graph.addNode(new Node2('D'));
		graph.addNode(new Node2('E'));
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
                graph.addEdge(1, 4); // I forgot this line in the video
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(4, 4);
		graph.addEdge(4, 2);
		
		graph.print();
		
		//System.out.println(graph.checkEdge(0, 1));
	}

}
