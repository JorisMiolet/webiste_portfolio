package maze_escape;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public abstract class AbstractGraph<V> {

    /** Graph representation:
     *  this class implements graph search algorithms on a graph with abstract vertex type V
     *  for every vertex in the graph, its neighbours can be found by use of abstract method getNeighbours(fromVertex)
     *  this abstraction can be used for both directed and undirected graphs
     **/

    public AbstractGraph() { }

    /**
     * retrieves all neighbours of the given fromVertex
     * if the graph is directed, the implementation of this method shall follow the outgoing edges of fromVertex
     * @param fromVertex
     * @return
     */
    public abstract Set<V> getNeighbours(V fromVertex);

    /**
     * retrieves all vertices that can be reached directly or indirectly from the given firstVertex
     * if the graph is directed, only outgoing edges shall be traversed
     * firstVertex shall be included in the result as well
     * if the graph is connected, all vertices shall be found
     * @param firstVertex   the start vertex for the retrieval
     * @return
     */
    public Set<V> getAllVertices(V firstVertex) {
        Set<V> visitedVertices = new HashSet<>();
        return getAllVerticesRecursive(firstVertex, visitedVertices);
    }

    private Set<V> getAllVerticesRecursive(V vertex, Set<V> visitedVertices) {
        Set<V> reachableVertices = new HashSet<>();

        visitedVertices.add(vertex);
        reachableVertices.add(vertex);

        Set<V> neighbors = getNeighbours(vertex);

        for (V neighbor : neighbors) {
            if (!visitedVertices.contains(neighbor)) {
                Set<V> recursiveVertices = getAllVerticesRecursive(neighbor, visitedVertices);
                reachableVertices.addAll(recursiveVertices);
            }
        }

        return reachableVertices;
    }



    /**
     * Formats the adjacency list of the subgraph starting at the given firstVertex
     * according to the format:
     *  	vertex1: [neighbour11,neighbour12,…]
     *  	vertex2: [neighbour21,neighbour22,…]
     *  	…
     * Uses a pre-order traversal of a spanning tree of the sub-graph starting with firstVertex as the root
     * if the graph is directed, only outgoing edges shall be traversed
     * , and using the getNeighbours() method to retrieve the roots of the child subtrees.
     * @param firstVertex
     * @return
     */
    public String formatAdjacencyList(V firstVertex) {
        StringBuilder stringBuilder = new StringBuilder("Graph adjacency list:\n");
        Set<V> visited = new HashSet<>();
        formatAdjacencyListRecursive(firstVertex,visited, stringBuilder);

        return stringBuilder.toString();
    }

    private void formatAdjacencyListRecursive(V vertex,Set<V> visited, StringBuilder stringBuilder) {
        visited.add(vertex);
        stringBuilder.append(vertex).append(": [");

        Set<V> neighbors = getNeighbours(vertex);
        String space = "";
        for (V neighbor : neighbors) {
            stringBuilder.append(space).append(neighbor);
            space = ",";
        }
        stringBuilder.append("]\n");
        for (V neighbor : neighbors) {
            if(!visited.contains(neighbor)){
                formatAdjacencyListRecursive(neighbor,visited,stringBuilder);
            }
        }


    }



    /**
     * represents a directed path of connected vertices in the graph
     */
    public class GPath {
        private Deque<V> vertices = new LinkedList<>();
        private double totalWeight = 0.0;
        private Set<V> visited = new HashSet<>();

        /**
         * representation invariants:
         * 1. vertices contains a sequence of vertices that are neighbours in the graph,
         *    i.e. FOR ALL i: 1 < i < vertices.length: getNeighbours(vertices[i-1]).contains(vertices[i])
         * 2. a path with one vertex equal start and target vertex
         * 3. a path without vertices is empty, does not have a start nor a target
         * totalWeight is a helper attribute to capture total path length from a function on two neighbouring vertices
         * visited is a helper set to be able to track visited vertices in searches, only for analysis purposes
         **/
        private static final int DISPLAY_CUT = 10;
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(
                    String.format("Weight=%.2f Length=%d visited=%d (",
                            this.totalWeight, this.vertices.size(), this.visited.size()));
            String separator = "";
            int count = 0;
            final int tailCut = this.vertices.size()-1 - DISPLAY_CUT;
            for (V v : this.vertices) {
                // limit the length of the text representation for long paths.
                if (count < DISPLAY_CUT || count > tailCut) {
                    sb.append(separator).append(v.toString());
                    separator = ", ";
                } else if (count == DISPLAY_CUT) {
                    sb.append(separator).append("...");
                }
                count++;
            }
            sb.append(")");
            return sb.toString();
        }

        /**
         * recalculates the total weight of the path from a given weightMapper that calculates the weight of
         * the path segment between two neighbouring vertices.
         * @param weightMapper
         */
        public void reCalculateTotalWeight(BiFunction<V,V,Double> weightMapper) {
            this.totalWeight = 0.0;
            V previous = null;
            for (V v: this.vertices) {
                // the first vertex of the iterator has no predecessor and hence no weight contribution
                if (previous != null) this.totalWeight += weightMapper.apply(previous, v);
                previous = v;
            }
        }

        public Queue<V> getVertices() {
            return this.vertices;
        }

        public double getTotalWeight() {
            return this.totalWeight;
        }

        public Set<V> getVisited() { return this.visited; }
    }

    /**
     * Uses a depth-first search algorithm to find a path from the startVertex to targetVertex in the subgraph
     * All vertices that are being visited by the search should also be registered in path.visited
     * @param startVertex
     * @param targetVertex
     * @return  the path from startVertex to targetVertex
     *          or null if target cannot be matched with a vertex in the sub-graph from startVertex
     */
    public GPath depthFirstSearch(V startVertex, V targetVertex) {
        Set<V> visited = new HashSet<>();
        return depthFirstSearchRecursive(startVertex, targetVertex, visited);
    }

    private GPath depthFirstSearchRecursive(V currentVertex, V targetVertex, Set<V> visited) {
        if(currentVertex == null || targetVertex == null)return null;
        if (visited.contains(currentVertex)) {
            return null;
        }
        visited.add(currentVertex);
        if(currentVertex.equals(targetVertex)){
            GPath gPath = new GPath();
            gPath.vertices.addLast(currentVertex);
            gPath.visited = visited;
            return gPath;
        }
        Set<V> neighbors = getNeighbours(currentVertex);
        for (V neighbor : neighbors) {
            GPath gPath = depthFirstSearchRecursive(neighbor, targetVertex, visited);
                if (gPath != null) {
                    gPath.vertices.addFirst(currentVertex);
                    return gPath;
                }
        }

        return null;
    }


    /**
     * Uses a breadth-first search algorithm to find a path from the startVertex to targetVertex in the subgraph
     * All vertices that are being visited by the search should also be registered in path.visited
     * @param startVertex
     * @param targetVertex
     * @return  the path from startVertex to targetVertex
     *          or null if target cannot be matched with a vertex in the sub-graph from startVertex
     */
    public GPath breadthFirstSearch(V startVertex, V targetVertex) {
        if (startVertex == null || targetVertex == null) return null;

        if(targetVertex.equals(startVertex)){
            GPath gPath = new GPath();
            gPath.visited.add(targetVertex);
            gPath.vertices.add(targetVertex);
            return gPath;
        }

        // Track visited vertices
        Set<V> accumulatedVisited = new HashSet<>();

        // Queue to perform BFS
        LinkedList<V> queue = new LinkedList<>();
        queue.add(startVertex);

        // Initialize path for each vertex
        Map<V, GPath> paths = new HashMap<>();
        for (V vertex : getAllVertices(startVertex)) {
            paths.put(vertex, new GPath());
        }

        // Initialize the start vertex's path
        GPath startPath = paths.get(startVertex);
        startPath.vertices.add(startVertex);

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
            GPath currentPath = paths.get(currentVertex);

            for (V neighbor : getNeighbours(currentVertex)) {
                if (!accumulatedVisited.contains(neighbor)) {
                    accumulatedVisited.add(neighbor);
                    queue.add(neighbor);

                    GPath neighborPath = new GPath();
                    neighborPath.vertices.addAll(currentPath.vertices); // Copy the previous vertices

                    neighborPath.vertices.add(neighbor); // Add the current neighbor

                    paths.put(neighbor, neighborPath);

                    if (neighbor.equals(targetVertex)) {
                        // Return the path if the target is found
                        GPath finalPath = paths.get(neighbor);
                        finalPath.visited = accumulatedVisited;
                        return finalPath;
                    }
                }
            }
        }

        // If targetVertex is unreachable
        return null;
    }



    // helper class to build the spanning tree of visited vertices in dijkstra's shortest path algorithm
    // your may change this class or delete it altogether follow a different approach in your implementation
    private class MSTNode implements Comparable<MSTNode> {
        protected V vertex;                // the graph vertex that is concerned with this MSTNode
        protected V parentVertex = null;     // the parent's node vertex that has an edge towards this node's vertex
        protected boolean marked = false;  // indicates DSP processing has been marked complete for this vertex
        protected double weightSumTo = Double.MAX_VALUE;   // sum of weights of current shortest path towards this node's vertex

        private MSTNode(V vertex) {
            this.vertex = vertex;
        }

        // comparable interface helps to find a node with the shortest current path, sofar
        @Override
        public int compareTo(MSTNode otherMSTNode) {
            return Double.compare(weightSumTo, otherMSTNode.weightSumTo);
        }
    }

    /**
     * Calculates the edge-weighted shortest path from the startVertex to targetVertex in the subgraph
     * according to Dijkstra's algorithm of a minimum spanning tree
     * @param startVertex
     * @param targetVertex
     * @param weightMapper   provides a function(v1,v2) by which the weight of an edge from v1 to v2
     *                       can be retrieved or calculated
     * @return  the shortest path from startVertex to targetVertex
     *          or null if target cannot be matched with a vertex in the sub-graph from startVertex
     */
    public GPath dijkstraShortestPath(V startVertex, V targetVertex, BiFunction<V, V, Double> weightMapper) {
        if (startVertex == null || targetVertex == null) return null;

        // Initialize the result path of the search
        GPath path = new GPath();

        // Minimum spanning tree which tracks for every visited vertex:
        // a) its (parent) predecessor in the currently shortest path towards this visited vertex
        // b) the total weight of the currently shortest path towards this visited vertex
        // c) a mark, indicating whether the current path towards this visited vertex is the final shortest.
        Map<V, MSTNode> minimumSpanningTree = new HashMap<>();

        // Initialize the minimum spanning tree with the startVertex
        MSTNode nearestMSTNode = new MSTNode(startVertex);
        nearestMSTNode.weightSumTo = 0.0;
        minimumSpanningTree.put(startVertex, nearestMSTNode);

        // Initialize priority queue for efficient retrieval of nearestMSTNode
        PriorityQueue<MSTNode> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(nearestMSTNode);

        // Continue Dijkstra's algorithm until the priority queue is empty
        while (!priorityQueue.isEmpty()) {
            nearestMSTNode = priorityQueue.poll();
            V currentVertex = nearestMSTNode.vertex;

            // Accumulate the visited vertices
            path.visited.add(currentVertex);

            // If targetVertex is reached, construct the final path and return it
            if (currentVertex.equals(targetVertex)) {
                return constructFinalPath(minimumSpanningTree, startVertex, targetVertex, weightMapper, path);
            }

            // Process neighbors
            for (V neighbor : getNeighbours(currentVertex)) {
                if (!path.visited.contains(neighbor)) {
                    double tentativeWeight = nearestMSTNode.weightSumTo + weightMapper.apply(currentVertex, neighbor);

                    // Update the MSTNode for the neighbor if a shorter path is found
                    MSTNode neighborNode = minimumSpanningTree.getOrDefault(neighbor, new MSTNode(neighbor));
                    if (tentativeWeight < neighborNode.weightSumTo) {
                        neighborNode.weightSumTo = tentativeWeight;
                        neighborNode.parentVertex = currentVertex; // Update the predecessor
                        minimumSpanningTree.put(neighbor, neighborNode);
                        priorityQueue.add(neighborNode);
                    }
                }
            }
        }

        // If the targetVertex is unreachable
        return null;
    }

    private GPath constructFinalPath(Map<V, MSTNode> minimumSpanningTree, V startVertex, V targetVertex,
                                     BiFunction<V, V, Double> weightMapper, GPath path) {
        V currentVertex = targetVertex;
        double totalWeight = 0.0;  // Initialize total weight

        while (currentVertex != null) {
            path.vertices.addFirst(currentVertex); // Add to the beginning to maintain the correct order

            MSTNode currentNode = minimumSpanningTree.get(currentVertex);
            if (currentNode.parentVertex != null) {
                // Update total weight for each edge
                totalWeight += weightMapper.apply(currentNode.parentVertex, currentVertex);
            }

            currentVertex = currentNode.parentVertex;
        }

        path.totalWeight = totalWeight;

        return path;
    }




}
