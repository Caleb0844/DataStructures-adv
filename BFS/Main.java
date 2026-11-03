package BFS;

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

       // graph.print();

        graph.breathFirstSerach(4);

        //System.out.println(graph.checkEdge(3,2));
    }
    
}

        // BDS(Breadth First Search Traverse a graph level by level)
        //             Utilizes a queue
        //             Better if destination is n average close to start
        //             Siblings are visited before children

        // DFS(Depth First Search)= Traverse a graph branch by branch
        //                         Utilizes a StackBetter if destination is on average far from the start
        //                         Children are visited before siblings 
        //                         More popular for games and puzzles