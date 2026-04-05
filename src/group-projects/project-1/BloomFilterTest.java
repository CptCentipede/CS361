import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter bloom = new BloomFilter();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Commands - add (word), check (word), runfiles, and exit"); // Added runfiles to instructions

        // testing loop that breaks on 'exit'
        while (true) {
            System.out.print(": "); // line visual for user input.
            String input = scanner.nextLine().trim(); // Added this .trim() to remove extra spaces

            // exit condition
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Passing the runfiles through
            if (input.equalsIgnoreCase("runfiles")) {
                runDatasetTest(bloom);
                continue;
            }
            String[] parts = input.split(" ");

            // checking for bad user input, i.e. only 'add' or only 'check' that isn't just exit
            if (parts.length < 2) {
                System.out.println("Try 'add (word)', 'check (word)', 'runfiles', or 'exit'");
                continue;
            }
            String command = parts[0];
            String word = parts[1];

            // reads first word of user input and handles add/check call, otherwise repeat commands.
            if (command.equalsIgnoreCase("add")) {
                bloom.insert(word);
                System.out.println("'" + word + "' added to Bloom filter.");
            } else if (command.equalsIgnoreCase("check")) {
                boolean result = bloom.check(word);
                if (result) {
                    System.out.println("'" + word + "' is PROBABLY in the set.");
                } else {
                    System.out.println("'" + word + "' is DEFINITELY NOT in the set.");
                }
            } else {
                System.out.println("Try 'add (word)', 'check (word)', 'runfiles', or 'exit'");
            }
        }

        scanner.close();
    }



    // Added this helper method so it runs the positive and negative cleaned files
    public static void runDatasetTest(BloomFilter bloom) {
        int positiveTotal = 0;
        int positivePassed = 0;

        int negativeTotal = 0;
        int falsePositives = 0;

        // query timing variables
        int queryCount = 0;
        long totalQueryTime = 0;

        try {
            String line;

            // BufferedReaders to read in the files
            // This one inserts every hash from the positive/train file
            BufferedReader positiveInsertReader = new BufferedReader(new FileReader("positiveSet_clean.txt"));
            while ((line = positiveInsertReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                bloom.insert(line);
            }
            positiveInsertReader.close(); // why do I keep forgetting to close it :C


            // Rechecking the positive/train file
            BufferedReader positiveCheckReader = new BufferedReader(new FileReader("positiveSet_clean.txt"));
            while ((line = positiveCheckReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                positiveTotal++;
                if (bloom.check(line)) {
                    positivePassed++;
                }
            }
            positiveCheckReader.close();



            // Checking the negative/test file
            BufferedReader negativeReader = new BufferedReader(new FileReader("negativeSet_clean.txt"));
            while ((line = negativeReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                negativeTotal++;

                long startTime = System.nanoTime();
                boolean result = bloom.check(line);
                long endTime = System.nanoTime();

                // time only the first 1000 queries
                if (queryCount < 1000) {
                    totalQueryTime += (endTime - startTime);
                    queryCount++;
                }

                if (result) {
                    falsePositives++;
                }
            }
            negativeReader.close();

            System.out.println("Positive checks passed: " + positivePassed + " / " + positiveTotal);
            System.out.println("Negative checks that returned true (false positives): " + falsePositives + " / " + negativeTotal);


            double falsePositiveRate = 0.0;
            if (negativeTotal > 0) {
                falsePositiveRate = (double) falsePositives / negativeTotal; // False positive rate conversion
            }

            System.out.println("False positive rate: " + falsePositiveRate);

            // average query time report
            if (queryCount > 0) {
                double averageQueryTimeNs = (double) totalQueryTime / queryCount;

                System.out.println("Query time test ran on " + queryCount + " keys.");
                System.out.println("Average time per query: " + averageQueryTimeNs + " ns");
            }

// Kept getting a few errors reading the files so threw this in here
        } catch (IOException e) {
            System.out.println("Error reading cleaned dataset files");
            e.printStackTrace();
        }
    }
}