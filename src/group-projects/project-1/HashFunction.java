import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {

    /*
     * Current setup
     * n = 550104  (number of inserted hashes from positiveSet_clean.txt)
     * p = 0.01    (false positive rate of 1%)
     * m = 5272779 (computed from the sizing formula for m)
     * k = 7       (computed from the sizing formula for k)
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

        if (passwordKey == null) {
            throw new IllegalArgumentException("passwordKey cannot be null");
        }

        if (m <= 0) {
            throw new IllegalArgumentException("m must be greater than 0");
        }

        if (k <= 0) {
            throw new IllegalArgumentException("k must be greater than 0");
        }

        int[] indices = new int[k];

        try {

            // Using SHA-256 once on the original key, then get two strong base hashes
            // from the digest and then double hashing:
            // index i = (h1 + i * h2) mod m
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(passwordKey.getBytes(StandardCharsets.UTF_8));

            long h1 = bytesToLong(digest, 0);   // first 8 bytes
            long h2 = bytesToLong(digest, 8);   // next 8 bytes

            // avoiding the zero step size
            if (h2 == 0) {
                h2 = 1;
            }

            for (int i = 0; i < k; i++) {
                long combined = h1 + (long) i * h2;
                indices[i] = (int) Math.floorMod(combined, m);
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 is not available on this system.", e);
        }

        return indices;
    }

    // Turning 8 bytes into one long value
    private static long bytesToLong(byte[] bytes, int start) {
        long value = 0L;

        for (int i = 0; i < 8; i++) {
            value = (value << 8) | (bytes[start + i] & 0xFFL);
        }

        return value;
    }
}
