package BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(new Node(5));
        tree.insert(new Node(8));
        tree.insert(new Node(3));
        tree.insert(new Node(6));
        tree.insert(new Node(2));
        tree.insert(new Node(6));
        tree.insert(new Node(1));
        tree.insert(new Node(9));
        tree.insert(new Node(4));

        tree.remove(0); 
        tree.display();

       // System.out.println(tree.search(1));

    }
    
}

                // Binary Search tree =Atree data structure where each node is greater than its left child,
                //                     but less than its right

                //                     benefit: easy to locate a node when they are in this order 

                //                     time complexity: best case O(log n)
                //                                     worst case O(n)

                //                     Space complexity: O(n)
