import java.util.Scanner;

public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter bloom = new BloomFilter();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Commands - add (word), check (word), and exit"); // instructions

        // testing loop that breaks on 'exit'
        while (true) {
            System.out.print(": "); // line visual for user input.
            String input = scanner.nextLine();

            // exit condition
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String[] parts = input.split(" ");

            // checking for bad user input, i.e. only 'add' or only 'check' that isn't just exit
            if (parts.length < 2) {
                System.out.println("Try 'add (word)', 'check (word)', or 'exit'");
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
                System.out.println("Try 'add (word)', 'check (word)', or 'exit'");
            }
        }
        scanner.close();
    }
}