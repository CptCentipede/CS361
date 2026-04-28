import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    public String name;
    public String type;
    public List<String> vertices;
    public Map<String, Integer> vertexIndex;
    public Map<String, List<Edge>> adjacencyList;
    public int[][] adjacencyMatrix;

    public Graph() {
        vertices = new ArrayList<>();
        vertexIndex = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    public static class Edge {
        public String neighbor;
        public int weight;

        public Edge(String neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }
}