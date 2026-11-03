# Data Structures and Algorithms Refresher

This is a study guide for the Java examples in this repository. The examples are small
and focus on the core idea of each data structure or algorithm. Most indexes in the
graph examples are integer positions into a node list.

## 1. Big-O and measuring performance

Big-O describes how an operation grows as the input size `n` grows:

- `O(1)` - constant time: one direct operation.
- `O(log n)` - logarithmic: the problem is repeatedly cut down, as in binary search.
- `O(n)` - linear: inspect each item once.
- `O(n log n)` - efficient comparison sorting, such as merge sort and average quicksort.
- `O(n^2)` - nested comparisons, common in simple sorting algorithms.
- `O(2^n)` - exponential growth; usually avoid unless the input is very small.

Space complexity describes additional memory. A recursive algorithm also uses call-stack
space. To measure elapsed time in Java:

```java
long start = System.nanoTime();
// code being measured
long elapsedNanos = System.nanoTime() - start;
double elapsedMillis = elapsedNanos / 1_000_000.0;
```

`Finale/Main.java` demonstrates this with a three-second sleep. A single timing run is
not a reliable benchmark: warm-up, garbage collection, the operating system, and input
size all affect the result. Benchmark repeated runs and compare the growth as input
changes.

## 2. Arrays and dynamic arrays

### Fixed arrays

An array stores elements in contiguous indexed positions. Reading `array[i]` is `O(1)`,
but inserting or deleting in the middle is `O(n)` because later values must shift.
Arrays have fixed length once created.

### `DynamicArrays/DynamArr.java`

`DynamArr` implements a simple resizable array using `Object[]`:

1. `add(data)` appends at `size`; if full, `grow()` doubles capacity.
2. `insert(index, data)` shifts values right, then writes the new value.
3. `delete(data)` finds the first matching value, shifts values left, clears the last
   slot, and shrinks when the size is at most one-third of capacity.
4. `search(data)` performs a linear search and returns an index or `-1`.
5. `isEmpty()` checks whether `size == 0`.
6. `toString()` displays the allocated array.

Typical costs: append is amortized `O(1)` (but a resize is `O(n)`), indexed access is
`O(1)`, insertion/deletion is `O(n)`, and search is `O(n)`. `DynamicArrays/Main.java`
shows growth, deletion, capacity, size, and emptiness.

Important Java improvement: use generics (`DynamicArray<T>`) instead of raw `Object`,
and use `.equals()` for object values rather than `==`.

### `LnkvArr/Main.java`

This compares `ArrayList<Integer>` and `LinkedList<Integer>` after inserting one million
values. An `ArrayList` is normally better for indexed access and removing at the end.
A `LinkedList` is useful for frequent operations at known ends, but finding an arbitrary
index requires traversal. Timing only one operation is illustrative, not a formal
benchmark.

## 3. Linked lists

### `LinkedLists/Main.java`

Java's `LinkedList` is a doubly linked list: each node has a value, a next reference,
and a previous reference. The example uses:

- `offer()` as queue-style insertion,
- `add(index, value)` for indexed insertion,
- `remove(value)` to delete a value,
- `peekFirst()` and `peekLast()` to inspect either end,
- `addFirst()`/`addLast()` and `removeFirst()`/`removeLast()` for end operations.

Advantages: dynamic size and efficient insertion/removal when the node position is
already known (`O(1)`). Disadvantages: extra pointer memory and no true random access;
search and indexed access are `O(n)`. Common uses include playlists, navigation,
queues, and stacks.

## 4. Stack

### `Stack/Main.java` and `Stack/Main1.java`

A stack is **LIFO** (last in, first out):

- `push(x)` adds to the top.
- `pop()` removes and returns the top.
- `peek()` returns the top without removing it.
- `search(x)` returns a one-based distance from the top, or `-1`.
- `isEmpty()` checks for no elements.

The Java `Stack` class works for learning, although `Deque`/`ArrayDeque` is usually
preferred in production. Stacks are used for undo/redo, browser history, function
calls, depth-first search, and expression parsing. Push, pop, and peek are `O(1)`.

## 5. Queues and priority queues

### `Queues/Main.java`

A queue is **FIFO** (first in, first out):

- `offer(x)` enqueues.
- `poll()` removes and returns the front, or returns `null` when empty.
- `peek()` inspects the front.
- `size()` and `isEmpty()` provide state.

The example uses `LinkedList` through the `Queue` interface. Queues model printer jobs,
keyboard buffers, customer lines, and breadth-first search. Enqueue and dequeue are
normally `O(1)`.

### `Queues/PriolityQueues.java`

A priority queue removes the highest- or lowest-priority item rather than the oldest
item. `new PriorityQueue<>(Collections.reverseOrder())` makes the largest GPA come out
first. `offer()` and `poll()` are `O(log n)`; inspecting the next item is `O(1)`.

## 6. Hash tables

### `HashTable/Main.java`

A hash table maps a unique key to a value:

1. A key's `hashCode()` is computed.
2. The hash is mapped to a bucket/index.
3. The key/value entry is stored in that bucket.
4. Collisions occur when different keys map to the same bucket; buckets must store
   multiple entries and compare keys to find the correct one.

Average insertion, lookup, and deletion are `O(1)`; a bad collision pattern can make
the worst case `O(n)`. `Hashtable` does not allow null keys or values and is an older
synchronized class. Prefer `HashMap` unless synchronized behavior is specifically
needed. The example prints `hashCode() % 21`; real library implementations handle
hash spreading and bucket details for you.

## 7. Searching

### Linear search - `LinearSearch/MainL.java`

Check each item from left to right until the target is found:

```text
for each index i:
    if array[i] == target: return i
return -1
```

Works on unsorted data. Best case `O(1)`, worst/average case `O(n)`, space `O(1)`.

### Binary search - `BinarySearch/Main.java`

Binary search requires a sorted array. Keep `low` and `high`, inspect the middle, and
discard the half that cannot contain the target:

```text
while low <= high:
    middle = low + (high - low) / 2
    if middle value is too small: low = middle + 1
    if middle value is too large: high = middle - 1
    otherwise return middle
return -1
```

Time is `O(log n)` and extra space is `O(1)`. The safe middle formula avoids integer
overflow. The example's diagnostic prints show the shrinking search interval.

### Interpolation search - `interpolationsearch/Main.java`

Interpolation search estimates where a value should be in a **sorted, approximately
uniformly distributed** numeric array. Its intended probe is:

```java
int probe = low + (value - array[low]) * (high - low)
                 / (array[high] - array[low]);
```

Average performance can approach `O(log log n)` on uniform data, but the worst case is
`O(n)`. It is not a good choice for clustered or irregular data. The repository code
uses `array[low]` instead of `value - array[low]`, so its probe calculation should be
corrected before relying on it; also guard against `array[high] == array[low]`.

## 8. Sorting

The repository's comparison examples all swap toward **descending** order because their
conditions use `<`. To sort ascending, reverse the comparison where appropriate.

### Bubble sort - `BubbleSort/Main.java`

Repeatedly compare neighboring values and swap them when out of order. After each pass,
one extreme value reaches its final position. Time is `O(n^2)`, space `O(1)`, and it is
stable if equal values are not swapped. The current condition sorts descending. An
optimization is to stop early when a pass makes no swaps.

### Insertion sort - `InsertionSort/Main.java`

Treat the left side as sorted. Save the current value, shift larger/smaller preceding
values until the insertion position is found, then insert the saved value. Time is
`O(n^2)` worst/average, `O(n)` best for nearly sorted input, space `O(1)`, and it is
stable. The current `array[j] < temp` condition builds descending order.

### Selection sort - `Selectionsort/Main.java`

For each output position, scan the remaining unsorted portion, select an extreme value,
and swap it into position. Time is `O(n^2)` in every case, space `O(1)`, and it usually
is not stable. The variable is called `min`, but `if (array[min] < array[j])` selects
the maximum and therefore sorts descending. For ascending order use
`array[min] > array[j]`.

### Merge sort - `MergeSort/Main.java`

1. Split the array into left and right halves.
2. Recursively sort each half.
3. Merge the two sorted halves by repeatedly taking the smaller front value.

Time is `O(n log n)` in all cases. Extra space is `O(n)` for temporary arrays. It is
stable when equal values are taken from the left first. The repository implementation
uses `<`, so equal values are taken from the right; it still sorts correctly but is not
strictly stable.

### Quick sort - `QuickSort/Main.java`

The example uses the last element as the pivot (Lomuto partition):

1. Move values smaller than the pivot toward the left.
2. Put the pivot between the smaller and larger partitions.
3. Recursively sort both partitions.

Average/best time is `O(n log n)`, worst time is `O(n^2)` when partitions are very
unbalanced, and average recursion space is `O(log n)` (worst `O(n)`). It sorts in place
but is not stable. Randomizing or choosing a median-like pivot reduces worst-case risk.

## 9. Recursion

### `Recursion/Main.java`

Shows the iterative baseline: a loop prints one step per iteration.

### `Recursion/Main1.java`

Shows a recursive method with:

- a **base case**: `steps < 1`, stop;
- a **recursive case**: print and call `walk(steps - 1)`.

Every recursive solution must move toward its base case or it will overflow the stack.

### `Recursion/Main2.java`

Factorial follows:

```text
factorial(n) = n * factorial(n - 1)
factorial(0) = 1
```

`factorial(5)` is `120`. Time and call-stack space are `O(n)`. The example treats values
below one as the base case, which is fine for the demonstration but does not validate
negative input as a mathematical factorial.

### `Recursion/Main3.java`

Power follows:

```text
power(base, exponent) = base * power(base, exponent - 1)
power(base, 0) = 1
```

The example computes `5^2 = 25`. The simple version takes `O(exponent)` time and stack
space. Exponentiation by squaring can reduce time to `O(log exponent)`.

## 10. Trees and binary search trees

### Tree vocabulary - `Trees/definations.md`

A tree is a non-linear hierarchy of nodes. The top node is the **root**. A node below
another node is its **child**; a node with no children is a **leaf**. A node's parent,
siblings, subtree, depth, and height describe its position and structure.

### `BinarySearchTree/Node.java`

Each node stores an integer and references to `left` and `right` children.

### `BinarySearchTree/BinarySearchTree.java`

A binary search tree (BST) maintains:

```text
values in left subtree < node value <= values in right subtree
```

This implementation inserts duplicates into the right subtree.

- `insert(node)`: recursively follows left/right links until a null position.
- `search(value)`: uses the ordering to discard one subtree at every level.
- `display()`: visits right, node, left, so it prints values in descending order. A
  left-node-right traversal would print ascending order.
- `remove(value)`: handles a leaf, a node with one child, or a node with two children.
  For two children it copies the successor (smallest value in the right subtree), or
  uses the predecessor (largest value in the left subtree) when there is no right child.

For a balanced tree, insert/search/remove are `O(log n)`. In the worst case a sorted
insertion order creates a chain and makes them `O(n)`. The tree uses `O(n)` storage.
`BinarySearchTree/Main.java` demonstrates insertion, a missing-value removal, and display.
For predictable performance, use a self-balancing tree such as Java's `TreeMap` or
`TreeSet`.

## 11. Graphs

A graph contains vertices (nodes) and edges (connections). These examples use directed
edges: `addEdge(src, dst)` creates `src -> dst`; it does not automatically create
`dst -> src`. For an undirected graph, add both directions.

### Adjacency matrix - `Graphs/DirectedGraphs/AdjacencyMatrix`

`Graph` stores an `int[v][v]` matrix. `matrix[src][dst] == 1` means an edge exists.
`addNode`, `addEdge`, `checkEdge`, and `print` are demonstrated in `Main.java`.

- Edge lookup: `O(1)`.
- Space: `O(v^2)`.
- Adding an edge: `O(1)`.
- Iterating neighbors: `O(v)`.

Matrices are good for dense graphs or frequent edge checks, but waste space on sparse
graphs.

### Adjacency list - `Graphs/DirectedGraphs/AdjacencyList`

`Graph` stores one linked list per vertex. The first item is that vertex; later items
are its outgoing neighbors. This is efficient for sparse graphs:

- Space: `O(v + e)`.
- Edge lookup: `O(degree(src))`, up to `O(v)` in this implementation.
- Iterating neighbors: `O(degree(src))`.

The example's `checkEdge` compares node references, which works because it retrieves the
same stored `Node` objects. A production implementation should use stable IDs or a
proper `equals`/`hashCode`.

### Breadth-first search - `BFS`

`BFS/Graph.java` uses a matrix and a `Queue<Integer>`:

1. Enqueue the starting vertex.
2. Mark it visited immediately.
3. Dequeue a vertex and process it.
4. Enqueue each unvisited neighbor.
5. Repeat until the queue is empty.

BFS explores level by level, so it finds the shortest number-of-edges path in an
unweighted graph. Time is `O(v^2)` with this matrix representation and space is `O(v)`.
The method is misspelled `breathFirstSerach`; remember the concept as **breadth-first
search**. `BFS/Node.java` stores a character label and `BFS/Main.java` builds and
traverses a five-node graph.

### Depth-first search - `DPS`

`DPS/Graph.java` uses recursive DFS:

1. If the vertex is already visited, return.
2. Mark and process it.
3. Recursively visit each adjacent vertex.
4. Returning from a call performs the backtracking.

DFS goes down one branch before trying siblings. It is useful for connectivity, cycle
detection, topological-style exploration, maze solving, and puzzle search. With the
matrix it takes `O(v^2)` time and `O(v)` visited/recursion space. The folder is named
`DPS`, but the algorithm is DFS; `DPS/MainY.java` builds a six-node graph and currently
prints the matrix while the DFS call is commented out.

## 12. Quick decision guide

| Need | Choose |
|---|---|
| Direct indexed access | Array or `ArrayList` |
| Flexible array size | `ArrayList` or a dynamic array |
| Fast operations at both ends | `ArrayDeque` |
| LIFO behavior | Stack / `Deque` |
| FIFO behavior | Queue / `ArrayDeque` |
| Always process the highest priority | `PriorityQueue` |
| Key/value lookup | `HashMap` |
| Search unsorted data | Linear search |
| Search sorted data | Binary search |
| Very uniform sorted numeric data | Interpolation search |
| Small or nearly sorted data | Insertion sort |
| Simple teaching implementation | Bubble/selection sort |
| Guaranteed `O(n log n)` sort | Merge sort |
| Fast average in-place sort | Quick sort |
| Hierarchical ordered lookup | Balanced BST / `TreeMap` |
| Sparse graph | Adjacency list |
| Dense graph or constant-time edge lookup | Adjacency matrix |
| Shortest unweighted graph path | BFS |
| Deep exploration/backtracking | DFS |

## 13. Refresher checklist

When revising an algorithm, ask:

1. What input assumptions does it require? (For example, binary search requires sorted
   data.)
2. What is the invariant? (For example, the left portion of insertion sort is sorted.)
3. What is the base case or stopping condition?
4. What happens for empty input, one item, duplicates, and a missing target?
5. What are time and extra-space costs in the best, average, and worst cases?
6. Is the operation stable, in-place, recursive, or dependent on input distribution?
7. Can the data structure representation change the complexity? A matrix and an
   adjacency list can represent the same graph with different costs.

The examples are intentionally introductory. Use them to remember the mechanism, then
practice tracing each algorithm by hand on a small input and test edge cases before
using it in a larger program.