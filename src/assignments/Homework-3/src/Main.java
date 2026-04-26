import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // The following is for problem 4 on the homework
    public static void main(String[] args) {
        // Adjacency array list for graph a with array list for each vertex
        List<List<Integer>> listA = new ArrayList<>();
        // Add edges to graph a
        listA.add(new ArrayList<>(Arrays.asList(2, 3, 7, 8)));
        listA.add(new ArrayList<>(Arrays.asList(2, 3, 4)));
        listA.add(new ArrayList<>(Arrays.asList(0, 1)));
        listA.add(new ArrayList<>(Arrays.asList(0, 1, 5)));
        listA.add(new ArrayList<>(Arrays.asList(1, 6)));
        listA.add(new ArrayList<>(Arrays.asList(3, 7)));
        listA.add(new ArrayList<>(Arrays.asList(4, 9, 11)));
        listA.add(new ArrayList<>(Arrays.asList(0, 5)));
        listA.add(new ArrayList<>(Arrays.asList(0, 10)));
        listA.add(new ArrayList<>(Arrays.asList(6, 11)));
        listA.add(new ArrayList<>(Arrays.asList(8)));
        listA.add(new ArrayList<>(Arrays.asList(6, 9)));
        
        // Adjacency array list for graph b with array list for each vertex
        List<List<Integer>> listB = new ArrayList<>();
        // Add edges to graph b
        listB.add(new ArrayList<>(Arrays.asList(1, 2)));
        listB.add(new ArrayList<>(Arrays.asList(3, 6)));
        listB.add(new ArrayList<>(Arrays.asList(3)));
        listB.add(new ArrayList<>(Arrays.asList(4)));
        listB.add(new ArrayList<>(Arrays.asList(5, 10)));
        listB.add(new ArrayList<>(Arrays.asList(6, 7, 12)));
        listB.add(new ArrayList<>(Arrays.asList(7, 10, 11, 13)));
        listB.add(new ArrayList<>(Arrays.asList(8)));
        listB.add(new ArrayList<>(Arrays.asList(9)));
        listB.add(new ArrayList<>(Arrays.asList(3, 7)));
        listB.add(new ArrayList<>(Arrays.asList(5, 11)));
        listB.add(new ArrayList<>());
        listB.add(new ArrayList<>(Arrays.asList(14)));
        listB.add(new ArrayList<>(Arrays.asList(14)));
        listB.add(new ArrayList<>());

        // Adjacency matrix for graph a
        int[][] matrixA = new int[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j : listA.get(i)) {
                matrixA[i][j] = 1;
            }
        }

        // Adjacency matrix for graph b
        int[][] matrixB = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j : listB.get(i)) {
                matrixB[i][j] = 1;
            }
        }

        // Run bfs and dfs for both graphs and both representations then print paths taken as well as average run time
        long startTime;
        
        // For graph a
        List<Integer> bfsListA = null, bfsMatA = null, dfsListA = null, dfsMatA = null;

        // BFS for list
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            bfsListA = bfs(listA, null, true, 12, 0, 7);
        }
        System.out.println("Graph A BFS list:   " + bfsListA + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // BFS for matrix
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            bfsMatA = bfs(null, matrixA, false, 12, 0, 7);
        }
        System.out.println("Graph A BFS matrix: " + bfsMatA + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // DFS for list
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            dfsListA = dfs(listA, null, true, 12, 0, 7);
        }
        System.out.println("Graph A DFS list:   " + dfsListA + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // DFS for matrix
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            dfsMatA = dfs(null, matrixA, false, 12, 0, 7);
        }
        System.out.println("Graph A DFS matrix: " + dfsMatA + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // For graph b
        List<Integer> bfsListB = null, bfsMatB = null, dfsListB = null, dfsMatB = null;

        // BFS for list
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            bfsListB = bfs(listB, null, true, 15, 0, 14);
        }
        System.out.println("Graph B BFS list:   " + toLetters(bfsListB) + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // BFS for matrix
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            bfsMatB = bfs(null, matrixB, false, 15, 0, 14);
        }
        System.out.println("Graph B BFS matrix: " + toLetters(bfsMatB) + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // DFS for list
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            dfsListB = dfs(listB, null, true, 15, 0, 14);
        }
        System.out.println("Graph B DFS list:   " + toLetters(dfsListB) + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");

        // DFS for matrix
        startTime = System.nanoTime();
        for (int i=0; i<5; i++){ 
            dfsMatB = dfs(null, matrixB, false, 15, 0, 14);
        }
        System.out.println("Graph B DFS matrix: " + toLetters(dfsMatB) + "  avg time: " + (System.nanoTime() - startTime) / 5000.0 + " us");
    }

    public static List<Integer> bfs(List<List<Integer>> adjList, int[][] adjMatrix, boolean useList,
                                int numberVertices, int source, int target) {
        // Track which nodes have already been visited
        boolean[] visited = new boolean[numberVertices];

        // Track the parent of each node in the BFS tree, used to reconstruct the path
        // at the end. Initialize to -1 to mark "no parent set yet"
        int[] parent = new int[numberVertices];
        for (int i = 0; i < numberVertices; i++) parent[i] = -1;

        // Queue of nodes to process
        List<Integer> queue = new ArrayList<>();

        // Start BFS from the source, add it to the queue, and mark it visited
        queue.add(source);
        visited[source] = true;
        int head = 0;
        
        // While there are still nodes in the queue to process
        while (head < queue.size()) {
            int currentNode = queue.get(head);
            head++;

            // If target reached, reconstruct path from source to target
            if (currentNode == target) {
                return reconstructPath(parent, target);
            }

            // Visit each unvisited neighbor of the current node, mark it, record its parent,
            //  and queue it. The list version reads neighbors directly and the matrix version
            //  scans the entire row to find them
            if (useList) {
                for (int nodeNeighbor : adjList.get(currentNode)) {
                    if (!visited[nodeNeighbor]) {
                        visited[nodeNeighbor] = true;
                        parent[nodeNeighbor] = currentNode;
                        queue.add(nodeNeighbor);
                    }
                }
            } else {
                for (int nodeNeighbor = 0; nodeNeighbor < numberVertices; nodeNeighbor++) {
                    if (adjMatrix[currentNode][nodeNeighbor] == 1 && !visited[nodeNeighbor]) {
                        visited[nodeNeighbor] = true;
                        parent[nodeNeighbor] = currentNode;
                        queue.add(nodeNeighbor);
                    }
                }
            }
        }
        // No path from source to target found
        return null;
    }

    public static List<Integer> dfs(List<List<Integer>> adjList, int[][] adjMatrix, boolean useList,
                                    int numberVertices, int source, int target) {
        // Track which nodes have already been visited
        boolean[] visited = new boolean[numberVertices];

        // Track the parent of each node in the BFS tree, used to reconstruct the path
        // at the end. Initialize to -1 to mark "no parent set yet"
        int[] parent = new int[numberVertices];
        for (int i=0; i<numberVertices; i++) parent[i] = -1;
        
        // Stack of nodes to process
        List<Integer> stack = new ArrayList<>();

        // Start DFS from the source, add it to the stack, and mark it visited
        stack.add(source);
        visited[source] = true;
        
        // While there are still nodes in the stack to process
        while (!stack.isEmpty()) {
            int currentNode = stack.remove(stack.size() - 1);

            // If target reached, reconstruct path from source to target
            if (currentNode == target) {
                return reconstructPath(parent, target);
            }

            // Visit each unvisited neighbor of the current node, mark it, record its parent,
            //  and stack it. The list version reads neighbors directly and the matrix version
            //  scans the entire row to find them
            if (useList) {
                for (int nodeNeighbor : adjList.get(currentNode)) {
                    if (!visited[nodeNeighbor]) {
                        visited[nodeNeighbor] = true;
                        parent[nodeNeighbor] = currentNode;
                        stack.add(nodeNeighbor);
                    }
                }
            } else {
                for (int nodeNeighbor = 0; nodeNeighbor < numberVertices; nodeNeighbor++) {
                    if (adjMatrix[currentNode][nodeNeighbor] == 1 && !visited[nodeNeighbor]) {
                        visited[nodeNeighbor] = true;
                        parent[nodeNeighbor] = currentNode;
                        stack.add(nodeNeighbor);
                    }
                }
            }
        }
        // No path from source to target found
        return null;
    }

    // Reconstruct the path from source to target using the parent array for printing results
    public static List<Integer> reconstructPath(int[] parent, int target) {
        List<Integer> path = new ArrayList<>();
        int currentNode = target;
        while (currentNode != -1) {
            path.add(0, currentNode);
            currentNode = parent[currentNode];
        }
        return path;
    }

    // This will change paths followed for graph b back into letters from integers for printing
    public static List<String> toLetters(List<Integer> path) {
        String[] labels = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};

        List<String> result = new ArrayList<>();
        for (int vertexNumber : path) result.add(labels[vertexNumber]);
        return result;
    }


    // The following is the code submitted to leetcode for the problem "Rotting Oranges" (https://leetcode.com/problems/rotting-oranges/)
    public int orangesRotting(int[][] grid) {
        // Create variables for matrix lengths, queue for 
        //  rotten oranges, and number of fresh oranges
        int rowLength = grid.length;
        int colLength = grid[0].length;
        List<int[]> queue = new ArrayList<>();
        int freshOrange = 0;

        // Add rotten oranges to queue and count fresh oranges
        for (int row=0; row<rowLength; row++) {
            for (int col=0; col<colLength; col++) {
                if (grid[row][col] == 2) {
                    queue.add(new int[]{row, col});
                } else if (grid[row][col] == 1) {
                    freshOrange++;
                }
            }
        }

        // Base case if no fresh oranges
        if (freshOrange == 0) return 0;

        // Create variables for recorded time, adjacent indices, 
        //  and front of the queue
        int minutes = 0;
        int[][] adjacentSpots = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
        int frontOfQueue = 0;

        // While the head is in the list and there are fresh oranges
        while (frontOfQueue < queue.size() && freshOrange > 0) {
            // Variable for end of round before moving to next minute
            int roundEnd = queue.size();

            // While the front of queue hasn't reached the end of the 
            //  queue for that round
            while(frontOfQueue < roundEnd) {
                // Create variable for coordinates of current position,
                //  store indices in own variables, and increment the  
                //  front of the queue for next pass
                int[] currentCoord = queue.get(frontOfQueue);
                int rowIndex = currentCoord[0];
                int colIndex = currentCoord[1];
                frontOfQueue++;

                // For each adjacent spot of rotten orange
                for (int[] direction : adjacentSpots) {
                    // Create variables for index of spot adjacent
                    int adjacentRow = rowIndex + direction[0];
                    int adjacentCol = colIndex + direction[1];

                    // If the position is within bounds and a fresh orange
                    if( adjacentRow>=0 && adjacentRow<rowLength && adjacentCol>=0 &&
                    adjacentCol<colLength && grid[adjacentRow][adjacentCol]==1) {
                        // Change to rotten orange, remove it from the fresh count,
                        //  and add the new rotten orange to the queue
                        grid[adjacentRow][adjacentCol] = 2;
                        freshOrange--;
                        queue.add(new int[]{adjacentRow, adjacentCol});
                    }
                }
            }
            // Add minute for this round
            minutes++;
        }
        // If all oranges have turned rotten return the number of minutes, otherwise
        //  return -1
        if (freshOrange == 0) return minutes;
        else return -1;
    }
}
