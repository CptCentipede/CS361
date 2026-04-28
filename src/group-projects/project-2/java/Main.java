import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] files = {
            "../graphs/sparse_1.txt",
            "../graphs/sparse_2.txt",
            "../graphs/dense_1.txt",
            // "../graphs/dense_2.txt",
            // "../graphs/dense_3.txt"
        };

        for (String file : files) {
            Graph g = GraphLoader.load(file);
            System.out.println("Loaded: " + g.name);
            System.out.println("  Type: " + g.type);
            System.out.println("  Vertices (" + g.vertices.size() + "): " + g.vertices);
            System.out.println("  Adjacency list entries: " + g.adjacencyList.size());
            System.out.println("  Matrix size: " + g.adjacencyMatrix.length + "x" + g.adjacencyMatrix[0].length);
            System.out.println();
        }
    }
}