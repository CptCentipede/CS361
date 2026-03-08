import java.util.Random;

public class Main {

    // This is for homework problem 2
    public static void main(String[] args) {
        int[] arrSizes = {200, 400, 600, 800, 1000};
        int[] kValues = {1, 2, 4, 8, 16, 32, 64};
        Random rand = new Random();

        System.out.println("n, k, averageTime");

        for (int n : arrSizes) {
            for (int k : kValues) {
                double totalTime = 0;

                for (int trial=0; trial<5; trial++) {
                    int[] arrayData = new int[n];

                    for (int i = 0; i < n; i++) {
                        arrayData[i] = rand.nextInt(10000);
                    }

                    long startTime = System.nanoTime();
                    hybridSort(arrayData, 0, arrayData.length - 1, k);
                    long endTime = System.nanoTime();

                    totalTime += (endTime - startTime) / 1_000_000.0;
                }

                double avgTime = totalTime / 5;
                System.out.printf("%d, %d, %.4f\n", n, k, avgTime);
            }
        }
    }

    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        int leftArrSize = middle - left + 1;
        int rightArrSize = right - middle;

        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];

        System.arraycopy(arr, left, leftArr, 0, leftArrSize);
        System.arraycopy(arr, middle + 1, rightArr, 0, rightArrSize);

        int i = 0, j = 0, k = left;
        while (i < leftArrSize && j < rightArrSize) {
            if (leftArr[i] <= rightArr[j]) arr[k++] = leftArr[i++];
            else arr[k++] = rightArr[j++];
        }
        while (i < leftArrSize) arr[k++] = leftArr[i++];
        while (j < rightArrSize) arr[k++] = rightArr[j++];
    }

    public static void hybridSort(int[] arr, int left, int right, int k) {
        if ((right - left + 1) <= k) {
            insertionSort(arr, left, right);
            return;
        } 
        
        if (left < right) {
            int middle = left + (right - left) / 2;
            hybridSort(arr, left, middle, k);
            hybridSort(arr, middle + 1, right, k);
            merge(arr, left, middle, right);
        }
    }
}