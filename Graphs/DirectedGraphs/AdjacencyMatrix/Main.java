package Graphs.DirectedGraphs.AdjacencyMatrix;

public class Main {
    public static void main(String[] args){
        Graph graph = new Graph(5);

        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addNode(new Node('D'));
        graph.addNode(new Node('E'));

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);

        graph.print();

        System.out.println(graph.checkEdge(3,2));
    }
    
}

        // Adjacency matrix = An array to store 1s and 0s to represent edges
        //                     num of rows = num of unique nodes
        //                     num of columns  = num of uique nodes

        //                     runtime complexity to check an edge: O(1)
        //                     pace complexity O (v{pow}2)