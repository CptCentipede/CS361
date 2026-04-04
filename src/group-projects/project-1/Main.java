/**
 * Assignment: Group Project 1
 * Class: CS 361
 * Authors: Miles Aether, Frank Hendrick, Francisco Herrera
 * Description: Part 1 implements a merge sort that splits the array into three subarrays then sorts each
 *              subarray recursively. Part 2 uses a datasest from haveibeenpwned.com to hash, insert into
 *              a bit array using insert logic that will allow the measure of false positives in terms of
 *              finding that password from the dataset.
 */

public class Main {
    public static void main(String[] args) {
        // Test the merge sort variant on integer arrays of size 2^20 to 2^30
        int arraySizePower = 20;
        // Use arrays of each provided power to test int arrays with merge sort
        while (arraySizePower <= 30) {
            int[] myArr = new int[(int) Math.pow(2, arraySizePower)];
            // Fill the array with random integers
            for (int i = 0; i < myArr.length; i++) {
                myArr[i] = (int) (Math.random() * 1000000);
            }
            long startTime = System.nanoTime();
            mergeSortVariantInt(myArr, 0, myArr.length - 1);
            long endTime = System.nanoTime();
            // Calculate the duration and print the results
            long duration = endTime - startTime;
            System.out.println("Merge Sort Variant Int Array Size Power: " + arraySizePower + " Time: " + duration + " nanoseconds");
            arraySizePower++;
        }

        // Test the merge sort variant on double arrays of size 2^20 to 2^30
        arraySizePower = 20;
        // Use arrays of each provided power to test double arrays with merge sort
        while (arraySizePower <= 30) {
            double[] myArr = new double[(int) Math.pow(2, arraySizePower)];
            // Fill the array with random doubles
            for (int i = 0; i < myArr.length; i++) {
                myArr[i] = Math.random() * 1000000;
            }
            long startTime = System.nanoTime();
            mergeSortVariantDouble(myArr, 0, myArr.length - 1);
            long endTime = System.nanoTime();
            // Calculate the duration and print the results
            long duration = endTime - startTime;
            System.out.println("Merge Sort Variant Double Array Size Power: " + arraySizePower + " Time: " + duration + " nanoseconds");
            arraySizePower++;
        }
    }

    // This method will implement three way merge sort on integer arrays
    // Recursively call merge sort on three subarrays, then merge the results
    public static void mergeSortVariantInt(int[] myArr, int left, int right) {
        // Base case: if the left index is greater than or equal to the right index, return
        if (left >= right) return;
        
        // Calculate the two middle indices to split the array into three parts
        int middle1 = left + (right - left) / 3;
        int middle2 = left + 2 * (right - left) / 3;

        // Recursively split into three subarrays
        mergeSortVariantInt(myArr, left, middle1);
        mergeSortVariantInt(myArr, middle1 + 1, middle2);
        mergeSortVariantInt(myArr, middle2 + 1, right);

        // Merge the three subarrays into sorted order
        mergeVariantInt(myArr, left, middle1, middle2, right);
    }
    // Merge three subarrays into one sorted array
    public static void mergeVariantInt(int[] myArr, int left, int middle1, int middle2, int right) {
        // Create a temporary array to hold the merged result
        int[] tempArr = new int[right - left + 1];

        // Variables to track the current index of each subarray and the temp array
        int i = left;
        int j = middle1 + 1;
        int k = middle2 + 1;
        int index = 0;

        // While all three subarrays have elements remaining
        while (i <= middle1 && j <= middle2 && k <= right) {
            if (myArr[i] <= myArr[j] && myArr[i] <= myArr[k])
                tempArr[index++] = myArr[i++];
            else if (myArr[j] <= myArr[i] && myArr[j] <= myArr[k])
                tempArr[index++] = myArr[j++];
            else
                tempArr[index++] = myArr[k++];
        }

        // Handle remaining elements from each pair of subarrays
        while (i <= middle1 && j <= middle2) {
            if (myArr[i] <= myArr[j]) tempArr[index++] = myArr[i++];
            else tempArr[index++] = myArr[j++];
        }
        while (j <= middle2 && k <= right) {
            if (myArr[j] <= myArr[k]) tempArr[index++] = myArr[j++];
            else tempArr[index++] = myArr[k++];
        }
        while (i <= middle1 && k <= right) {
            if (myArr[i] <= myArr[k]) tempArr[index++] = myArr[i++];
            else tempArr[index++] = myArr[k++];
        }

        // Copy any leftover elements
        while (i <= middle1) tempArr[index++] = myArr[i++];
        while (j <= middle2) tempArr[index++] = myArr[j++];
        while (k <= right) tempArr[index++] = myArr[k++];

        // Copy temporary array back into original array
        for (int x = 0; x < tempArr.length; x++)
            myArr[left + x] = tempArr[x]; 
    }

    // This method will implement three way merge sort on integer arrays
    // Merge three subarrays into one sorted array
    public static void mergeSortVariantDouble(double[] myArr, int left, int right) {
        // Base case: if the left index is greater than or equal to the right index, return
        if (left >= right) return;
        
        // Calculate the two middle indices to split the array into three parts
        int middle1 = left + (right - left) / 3;
        int middle2 = left + 2 * (right - left) / 3;

        // Recursively split into three subarrays
        mergeSortVariantDouble(myArr, left, middle1);
        mergeSortVariantDouble(myArr, middle1 + 1, middle2);
        mergeSortVariantDouble(myArr, middle2 + 1, right);

        // Merge the three subarrays into sorted order
        mergeVariantDouble(myArr, left, middle1, middle2, right);
    }
    // Merge three subarrays into one sorted array
    public static void mergeVariantDouble(double[] myArr, int left, int middle1, int middle2, int right) {
        // Create a temporary array to hold the merged result
        double[] tempArr = new double[right - left + 1];

        // Variables to track the current index of each subarray and the temp array
        int i = left;
        int j = middle1 + 1;
        int k = middle2 + 1;
        int index = 0;

        // While all three subarrays have elements remaining
        while (i <= middle1 && j <= middle2 && k <= right) {
            if (myArr[i] <= myArr[j] && myArr[i] <= myArr[k])
                tempArr[index++] = myArr[i++];
            else if (myArr[j] <= myArr[i] && myArr[j] <= myArr[k])
                tempArr[index++] = myArr[j++];
            else
                tempArr[index++] = myArr[k++];
        }

        // Handle remaining elements from each pair of subarrays
        while (i <= middle1 && j <= middle2) {
            if (myArr[i] <= myArr[j]) tempArr[index++] = myArr[i++];
            else tempArr[index++] = myArr[j++];
        }
        while (j <= middle2 && k <= right) {
            if (myArr[j] <= myArr[k]) tempArr[index++] = myArr[j++];
            else tempArr[index++] = myArr[k++];
        }
        while (i <= middle1 && k <= right) {
            if (myArr[i] <= myArr[k]) tempArr[index++] = myArr[i++];
            else tempArr[index++] = myArr[k++];
        }

        // Copy any leftover elements
        while (i <= middle1) tempArr[index++] = myArr[i++];
        while (j <= middle2) tempArr[index++] = myArr[j++];
        while (k <= right) tempArr[index++] = myArr[k++];

        // Copy temporary array back into original array
        for (int x = 0; x < tempArr.length; x++)
            myArr[left + x] = tempArr[x]; 
    }
}