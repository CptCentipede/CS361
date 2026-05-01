import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Sparse and dense graphs represented as both adjacency matrices and adjacency lists
        // Graph Sparse 1
        String[] labelsSparse1 = {"A", "B", "C", "D", "E", "F"};
        List<List<int[]>> listSparse1 = new ArrayList<>();
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{1, 4}, new int[]{2, 2})));
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{0, 4}, new int[]{3, 5})));
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{0, 2}, new int[]{3, 1})));
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{1, 5}, new int[]{2, 1}, new int[]{4, 3})));
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{3, 3}, new int[]{5, 2})));
        listSparse1.add(new ArrayList<>(Arrays.asList(new int[]{4, 2})));

        int[][] matrixSparse1 = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int[] edge : listSparse1.get(i)) {
                matrixSparse1[i][edge[0]] = edge[1];
            }
        }

        // Graph Sparse 2
        String[] labelsSparse2 = {"1", "2", "3", "4", "5", "6", "7"};
        List<List<int[]>> listSparse2 = new ArrayList<>();
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 6})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{0, 3}, new int[]{3, 2}, new int[]{4, 5})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{0, 6}, new int[]{4, 4})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{1, 2}, new int[]{5, 7})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{1, 5}, new int[]{2, 4}, new int[]{6, 1})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{3, 7})));
        listSparse2.add(new ArrayList<>(Arrays.asList(new int[]{4, 1})));

        int[][] matrixSparse2 = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int[] edge : listSparse2.get(i)) {
                matrixSparse2[i][edge[0]] = edge[1];
            }
        }

        // Graph Dense 1
        String[] labelsDense1 = {"A", "B", "C", "D", "E"};
        List<List<int[]>> listDense1 = new ArrayList<>();
        listDense1.add(new ArrayList<>(Arrays.asList(new int[]{1, 2}, new int[]{2, 5}, new int[]{3, 1}, new int[]{4, 4})));
        listDense1.add(new ArrayList<>(Arrays.asList(new int[]{0, 2}, new int[]{2, 3}, new int[]{3, 2}, new int[]{4, 6})));
        listDense1.add(new ArrayList<>(Arrays.asList(new int[]{0, 5}, new int[]{1, 3}, new int[]{3, 3}, new int[]{4, 1})));
        listDense1.add(new ArrayList<>(Arrays.asList(new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{4, 2})));
        listDense1.add(new ArrayList<>(Arrays.asList(new int[]{0, 4}, new int[]{1, 6}, new int[]{2, 1}, new int[]{3, 2})));

        int[][] matrixDense1 = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int[] edge : listDense1.get(i)) {
                matrixDense1[i][edge[0]] = edge[1];
            }
        }

        // Graph Dense 2
        String[] labelsDense2 = {"1", "2", "3", "4", "5", "6"};
        List<List<int[]>> listDense2 = new ArrayList<>();
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 2}, new int[]{3, 6}, new int[]{4, 5}, new int[]{5, 4})));
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{0, 3}, new int[]{2, 1}, new int[]{3, 2}, new int[]{4, 4}, new int[]{5, 7})));
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{0, 2}, new int[]{1, 1}, new int[]{3, 3}, new int[]{4, 6}, new int[]{5, 5})));
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{0, 6}, new int[]{1, 2}, new int[]{2, 3}, new int[]{4, 2}, new int[]{5, 4})));
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{0, 5}, new int[]{1, 4}, new int[]{2, 6}, new int[]{3, 2}, new int[]{5, 1})));
        listDense2.add(new ArrayList<>(Arrays.asList(new int[]{0, 4}, new int[]{1, 7}, new int[]{2, 5}, new int[]{3, 4}, new int[]{4, 1})));

        int[][] matrixDense2 = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int[] edge : listDense2.get(i)) {
                matrixDense2[i][edge[0]] = edge[1];
            }
        }

        String[] labelsDense3 = {"A", "B", "C", "D", "E", "F", "G"};
        List<List<int[]>> listDense3 = new ArrayList<>();
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{1, 4}, new int[]{2, 2}, new int[]{3, 7}, new int[]{4, 3}, new int[]{5, 5}, new int[]{6, 6})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 4}, new int[]{2, 1}, new int[]{3, 5}, new int[]{4, 8}, new int[]{5, 2}, new int[]{6, 4})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 2}, new int[]{1, 1}, new int[]{3, 3}, new int[]{4, 6}, new int[]{5, 4}, new int[]{6, 7})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 7}, new int[]{1, 5}, new int[]{2, 3}, new int[]{4, 2}, new int[]{5, 9}, new int[]{6, 1})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 3}, new int[]{1, 8}, new int[]{2, 6}, new int[]{3, 2}, new int[]{5, 5}, new int[]{6, 3})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 5}, new int[]{1, 2}, new int[]{2, 4}, new int[]{3, 9}, new int[]{4, 5}, new int[]{6, 2})));
        listDense3.add(new ArrayList<>(Arrays.asList(new int[]{0, 6}, new int[]{1, 4}, new int[]{2, 7}, new int[]{3, 1}, new int[]{4, 3}, new int[]{5, 2})));

        int[][] matrixDense3 = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int[] edge : listDense3.get(i)) {
                matrixDense3[i][edge[0]] = edge[1];
            }
        }

        runAndPrint("Sparse 1", matrixSparse1, listSparse1, labelsSparse1, 0);
        runAndPrint("Sparse 2", matrixSparse2, listSparse2, labelsSparse2, 0);
        runAndPrint("Dense 1",  matrixDense1,  listDense1,  labelsDense1,  0);
        runAndPrint("Dense 2",  matrixDense2,  listDense2,  labelsDense2,  0);
        runAndPrint("Dense 3",  matrixDense3,  listDense3,  labelsDense3,  0);
    }

    private static void runAndPrint(String name, int[][] matrix, List<List<int[]>> list,
                                    String[] labels, int source) {
        final int TRIALS = 5;

        // Count V and E
        int V = matrix.length;
        int E = 0;
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if (matrix[i][j] > 0) E++;
        E /= 2;

        System.out.println(name + ", source = " + labels[source] + " (V=" + V + ", E=" + E + ")");

        // Warmup run to mitigate JVM optimizations affecting timing
        DijkstraArray.runMatrix(matrix, source); 

        Runtime rt = Runtime.getRuntime();

        // Matrix version (averaged over 5 trials with 1 warmup run)
        double matrixTimeTotal = 0;
        double matrixMemTotal = 0;
        DijkstraArray.Result rMatrix = null;

        for (int t = 0; t < TRIALS; t++) {
            System.gc();
            long memBefore = rt.totalMemory() - rt.freeMemory();
            long start = System.nanoTime();
            rMatrix = DijkstraArray.runMatrix(matrix, source);
            long end = System.nanoTime();
            long memAfter = rt.totalMemory() - rt.freeMemory();
            matrixTimeTotal += (end - start) / 1_000_000.0;
            matrixMemTotal += Math.max(0, memAfter - memBefore) / 1024.0;
        }

        double matrixTimeAvg = matrixTimeTotal / TRIALS;
        double matrixMemAvg = matrixMemTotal / TRIALS;

        System.out.printf("  Matrix: avg time = %.4f ms, avg memory = %.2f KB%n",
            matrixTimeAvg, matrixMemAvg);
        printResult(rMatrix, labels, source);

        // List version (averaged over 5 trials with 1 warmup run)
        // Warmup run to mitigate JVM optimizations affecting timing
        DijkstraArray.runList(list, source);

        double listTimeTotal = 0;
        double listMemTotal = 0;
        DijkstraArray.Result rList = null;

        for (int t = 0; t < TRIALS; t++) {
            System.gc();
            long memBefore = rt.totalMemory() - rt.freeMemory();
            long start = System.nanoTime();
            rList = DijkstraArray.runList(list, source);
            long end = System.nanoTime();
            long memAfter = rt.totalMemory() - rt.freeMemory();
            listTimeTotal += (end - start) / 1_000_000.0;
            listMemTotal += Math.max(0, memAfter - memBefore) / 1024.0;
        }

        double listTimeAvg = listTimeTotal / TRIALS;
        double listMemAvg = listMemTotal / TRIALS;

        System.out.printf("  List: avg time = %.4f ms, avg memory = %.2f KB%n",
            listTimeAvg, listMemAvg);
        printResult(rList, labels, source);
    }

    private static void printResult(DijkstraArray.Result r, String[] labels, int source) {
        for (int i = 0; i < r.dist.length; i++) {
            String d = (r.dist[i] == DijkstraArray.INF) ? "INF" : String.valueOf(r.dist[i]);
            List<Integer> path = DijkstraArray.reconstructPath(r.parent, source, i);
            StringBuilder pathStr = new StringBuilder();
            for (int j = 0; j < path.size(); j++) {
                if (j > 0) pathStr.append(" -> ");
                pathStr.append(labels[path.get(j)]);
            }
            System.out.println("    to " + labels[i] + ": dist=" + d + ", path=" + pathStr);
        }
        System.out.println();
    }
}