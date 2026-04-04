import java.io.*;
import java.util.Arrays;

/*
Used this in terminal to run and make sure it passes the 4 tests requiered before moving to phase 3:
javac HashFunction.java HashFunctionTest.java
java HashFunctionTest
*/

public class HashFunctionTest {  //Hash Function tester to make sure it passes the 4 requiered things before moving on to phase 3. 

    public static void main(String[] args) {

        int m = 5272779;
        int k = 7; 

        try { // try reading sample hashes from the cleaned files

            BufferedReader positiveReader = new BufferedReader(new FileReader("positiveSet_clean.txt")); // open positive file
            BufferedReader negativeReader = new BufferedReader(new FileReader("negativeSet_clean.txt")); // open negative file

            String key1 = positiveReader.readLine(); // read the first cleaned hash from the positive set
            String key2 = negativeReader.readLine(); // read the first cleaned hash from the negative set

            positiveReader.close();
            negativeReader.close(); 

            int[] result1 = HashFunction.getHashIndices(key1, m, k); //run hash function on key1
            int[] result2 = HashFunction.getHashIndices(key1, m, k); //run it again on same key to test determinism
            int[] result3 = HashFunction.getHashIndices(key2, m, k); //run on different key to compare outputs

            System.out.println("key1 = " + key1);
            System.out.println("key2 = " + key2);

            System.out.println("result1 = " + Arrays.toString(result1)); // first output
            System.out.println("result2 = " + Arrays.toString(result2)); // repeated same-input output
            System.out.println("result3 = " + Arrays.toString(result3)); // different-input output



            // Test 1: exactly k indices
            if (result1.length == k) { // check whether array length is exactly k
                System.out.println("PASS: returns exactly k indices");
            } else {
                System.out.println("FAIL: does not return exactly k indices");
            }





            // Test 2: every index is within [0, m)
            boolean allInBounds = true; // True unless theres a bad index
            for (int index : result1) { // check each returned index
                if (index < 0 || index >= m) { // if index is outside the legal range [0, m)
                    allInBounds = false; // It fails
                    break;
                }
            }
            if (allInBounds) {
                System.out.println("PASS: every index is within [0, m)");
            } else {
                System.out.println("FAIL: an index was outside [0, m)");
            }






            // Test 3: same input returns the same indices
            if (Arrays.equals(result1, result2)) { //Comparing same key runs
                System.out.println("PASS: same input gives same output");
            } else {
                System.out.println("FAIL: same input did not give same output");
            }






            // Test 4: different inputs return different indices
            if (!Arrays.equals(result1, result3)) { //Comparing different key runs
                System.out.println("PASS: different inputs give different output");
            } else {
                System.out.println("FAIL: different inputs gave same output");
            }





        } catch (IOException e) { // if file reading fails, come here and throw an error msg
            System.out.println("Could not read cleaned test files.");
            e.printStackTrace();
        }
    }
}
