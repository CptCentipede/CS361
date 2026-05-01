import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DijkstraArray {

    public static final int INF = Integer.MAX_VALUE;

    public static class Result {
        public int[] dist;
        public int[] parent;

        public Result(int[] dist, int[] parent) {
            this.dist = dist;
            this.parent = parent;
        }
    }

    public static Result runMatrix(int[][] matrix, int source) {
        int V = matrix.length;
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[source] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            if (u == -1) break;
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                int weight = matrix[u][v];
                if (weight > 0 && !visited[v] && dist[u] != INF) {
                    int newDist = dist[u] + weight;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
        }

        return new Result(dist, parent);
    }

    public static Result runList(List<List<int[]>> adjList, int source) {
        int V = adjList.size();
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[source] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < V; v++) {
                if (!visited[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            if (u == -1) break;
            visited[u] = true;

            for (int[] edge : adjList.get(u)) {
                int v = edge[0];
                int weight = edge[1];
                if (!visited[v] && dist[u] != INF) {
                    int newDist = dist[u] + weight;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        parent[v] = u;
                    }
                }
            }
        }

        return new Result(dist, parent);
    }

    public static List<Integer> reconstructPath(int[] parent, int source, int target) {
        List<Integer> path = new ArrayList<>();
        if (parent[target] == -1 && target != source) return path;
        
        int current = target;
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        return path;
    }
}