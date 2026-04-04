import java.util.BitSet;

public class BloomFilter {
    private static final int M = 4792000; // size of bit array, calculated from equation in project pdf
    private static final int K = 7; // number of hash functions, can be adjusted based on our HashFunction class
    private BitSet bits;

    public BloomFilter() {
        bits = new BitSet(M);
    }

    // add item, i.e. set bit i to 1
    public void insert(String item) {
        int[] hashes = HashFunction.getHashIndices(item, M, K); // runs item through all k hash functions

        for (int i = 0; i < K; i++) { // sets each bit of k hashed item to 1
            int index = Math.abs(hashes[i]) % M;
            //System.out.printf("%d, ", index); // for testing.

            bits.set(index);
        }
    }

    // check, i.e. is bit i 0 or 1
    public boolean check(String item) {
        int[] hashes = HashFunction.getHashIndices(item, M, K);

        for (int i = 0; i < K; i++) {
            int index = Math.abs(hashes[i]) % M;
            //System.out.printf("%d, ", index); // for testing.

            if (!bits.get(index)) {
                return false; // definitely not present
            }
        }
        return true; // probably present
    }
}