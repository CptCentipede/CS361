public class HashFunction {

    /*
     * Current setup:
     * n = 550104  (number of inserted hashes from positiveSet_clean.txt)
     * p = 0.01    (false positive rate of 1%)
     * m = 5272779 (computed from the sizing formula for m)
     * k = 7       (computed from the sizing formula for k )
     *
     * Input:
     *   passwordKey = one cleaned hash string
     *   m = size of bit array
     *   k = number of hash functions / positions
     *
     * Output:
     *   int[] containing exactly k indices
     *
     * Rule:
     *   every index must be in [0, m)
     */


    public static int[] getHashIndices(String passwordKey, int m, int k) { //Method that returns k hash positions

        if (passwordKey == null) { // making sure input exists
            throw new IllegalArgumentException("passwordKey cannot be null"); // stop if it does not exist
        }

        if (m <= 0) { // making sure bit array size is legal
            throw new IllegalArgumentException("m must be greater than 0"); // stop if bit array size is illegal
        }

        if (k <= 0) { // making sure number of hash functions is legal
            throw new IllegalArgumentException("k must be greater than 0"); // stop if number of hash functions is illegal
        }


        int[] indices = new int[k]; //Array that will hold exactly k positions




        // The salted part makes each hash function's input a little different.
        // If the input were exactly the same every time, we would get the same number 7 times,
        // and all 7 hash functions would point to the same bit-array position.
        // So the salted part helps produce different indices for the same original key.
        for (int i = 0; i < k; i++) { //each loop pass acts like one hash function and produces one index -> (7 hash functions, 7 indices)
            String saltedKey = "hashFunction" + i + ":" + passwordKey; // making a fixed modified version of the key for this hash function
            int hashValue = saltedKey.hashCode(); // hash this version of the key
            indices[i] = Math.floorMod(hashValue, m); // wrapping the result into the valid range [0, m)

        }

        return indices; // return the full array of k positions
    }
}
