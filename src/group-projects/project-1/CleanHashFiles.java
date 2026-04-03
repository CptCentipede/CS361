import java.io.*;

//Little cleaner to remove the :count part of the positive & negative .txt files

public class CleanHashFiles {
    public static void main(String[] args) {
        cleanFile("positiveSet.txt", "positiveSet_clean.txt");
        cleanFile("negativeSet.txt", "negativeSet_clean.txt");
        System.out.println("Finished cleaning both files."); }

    public static void cleanFile(String inputFile, String outputFile) {  
        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) 
                    continue;

                String hash = line.split(":")[0];
                bw.write(hash);
                bw.newLine();
            }

            System.out.println("Created: " + outputFile);

        } catch (IOException e) {
            System.out.println("Could not process " + inputFile);
            e.printStackTrace();
        }
    }
}
