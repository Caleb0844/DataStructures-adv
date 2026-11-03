        // DPS(Depth First Search) is a search algorithm for traversing a tree or a graph structure

        // 1. Pick a route

        // 2. Keep going until you reach the dead end, or a previously visited node

        // 3.Backtrack to last node that has unvisited adjacent neighbours 

        
public class MainY {
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

       // graph.print();

        graph.depthFirstSearch(4);

      //  System.out.println(graph.checkEdge(3,2));
    }
    
}