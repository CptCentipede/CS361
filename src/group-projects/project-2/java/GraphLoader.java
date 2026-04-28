import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphLoader {

    public static Graph load(String filepath) throws IOException {
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("NAME ")) {
                    graph.name = line.substring(5).trim();
                } else if (line.startsWith("TYPE ")) {
                    graph.type = line.substring(5).trim();
                } else if (line.startsWith("VERTICES ")) {
                    // next non-empty line contains the vertex labels
                    String vertexLine;
                    while ((vertexLine = br.readLine()) != null && vertexLine.trim().isEmpty()) {}
                    String[] labels = vertexLine.trim().split("\\s+");
                    for (int i = 0; i < labels.length; i++) {
                        graph.vertices.add(labels[i]);
                        graph.vertexIndex.put(labels[i], i);
                        graph.adjacencyList.put(labels[i], new ArrayList<>());
                    }
                } else if (line.equals("ADJACENCY_LIST")) {
                    parseAdjacencyList(br, graph);
                } else if (line.equals("ADJACENCY_MATRIX")) {
                    parseAdjacencyMatrix(br, graph);
                }
            }
        }

        return graph;
    }

    private static void parseAdjacencyList(BufferedReader br, Graph graph) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("END")) break;
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            String vertex = tokens[0];
            List<Graph.Edge> edges = graph.adjacencyList.get(vertex);
            for (int i = 1; i < tokens.length; i += 2) {
                String neighbor = tokens[i];
                int weight = Integer.parseInt(tokens[i + 1]);
                edges.add(new Graph.Edge(neighbor, weight));
            }
        }
    }

    private static void parseAdjacencyMatrix(BufferedReader br, Graph graph) throws IOException {
        int V = graph.vertices.size();
        graph.adjacencyMatrix = new int[V][V];

        String line;
        int row = 0;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("END")) break;
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            for (int col = 0; col < tokens.length; col++) {
                graph.adjacencyMatrix[row][col] = Integer.parseInt(tokens[col]);
            }
            row++;
        }
    }
}