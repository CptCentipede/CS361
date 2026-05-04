import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DijkstraArray {

    // Represent infinity as the maximum integer value for unreachable vertices
    public static final int INF = Integer.MAX_VALUE;

    // Result class to hold the shortest distances and parent pointers for path reconstruction
    public static class Result {
        public int[] dist;
        public int[] parent;

        public Result(int[] dist, int[] parent) {
            this.dist = dist;
            this.parent = parent;
        }
    }

    // Dijkstra's algorithm using an adjacency matrix representation
    public static Result runMatrix(int[][] matrix, int source) {
        int V = matrix.length;
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        // Initialize distances to infinity and parent pointers to -1 due to no parent
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        // Distance to the source vertex is 0 from iteself
        dist[source] = 0;

        // Iterate through each vertex
        for (int count = 0; count < V; count++) {
            // Find the unvisited vertex with the smallest tentative distance
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // If no vertex is reachable, break out of the loop
            if (u == -1) break;
            visited[u] = true;

            // Relax edges from vertex u to its neighbors
            for (int v = 0; v < V; v++) {
                int weight = matrix[u][v];
                // If a valid edge exists, its weight is positive, 
                // neighbor is not visited, and the current vertex is reachable
                if (weight > 0 && !visited[v] && dist[u] != INF) {
                    int newDist = dist[u] + weight;
                    // If this path is shorter than the current best, update distance and parent
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
        }

        return new Result(dist, parent);
    }

    // Dijkstra's algorithm using an adjacency list representation
    public static Result runList(List<List<int[]>> adjList, int source) {
        int V = adjList.size();
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        // Initialize distances to infinity and parent pointers to -1 due to no parent
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        // Distance to the source vertex is 0 from iteself
        dist[source] = 0;

        // Iterate through each vertex
        for (int count = 0; count < V; count++) {
            // Find the unvisited vertex with the smallest tentative distance
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // If no vertex is reachable, break out of the loop
            if (u == -1) break;
            visited[u] = true;

            // Relax edges from vertex u to its neighbors in the adjacency list
            for (int[] edge : adjList.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                // If a valid edge exists, its weight is positive, 
                // neighbor is not visited, and the current vertex is reachable
                if (!visited[v] && dist[u] != INF) {
                    int newDist = dist[u] + weight;
                    // If this path is shorter than the current best, update distance and parent
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
        }

        return new Result(dist, parent);
    }

    // Walks the parent array backwards from target to source to reconstruct the shortest path.
    // Returns an empty list if the target is unreachable from the source.
    public static List<Integer> reconstructPath(int[] parent, int source, int target) {
        List<Integer> path = new ArrayList<>();
        // Target has no predecessor and isn't the source itself, so it's unreachable
        if (parent[target] == -1 && target != source) return path;
        
        // Walk backwards from target to source using the parent pointers
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        // Path was built target-first, so reverse it to read source-to-target
        Collections.reverse(path);
        return path;
    }
}