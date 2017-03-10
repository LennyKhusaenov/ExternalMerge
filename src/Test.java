import java.io.*;

import java.util.Random;


public class Test {
    private static final int FILE_SIZE = 100_000_000;
    private static final int MAX_STRING_LENGTH = 100;

    private static void fileGenerator(String fileName) throws IOException {

        String testSymbols = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ ";
        PrintWriter fileWriter = new PrintWriter(fileName);
        File newFile = new File(fileName);
        Random rand = new Random();

        while (newFile.length() < FILE_SIZE) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < rand.nextInt(MAX_STRING_LENGTH); i++) {
                stringBuilder.append(testSymbols.charAt(rand.nextInt(testSymbols.length())));
            }
            fileWriter.println(stringBuilder.toString());
        }
        System.out.println("Size of generated file "+newFile.length() / 1_000_000 +"MB");
        fileWriter.close();
    }


    public static void main(String[] args) throws IOException {

        System.out.println("Stand by, test file generating (default - input.txt)");
        fileGenerator("input.txt");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the name of the file you want to sort");
        String inputFileName = bf.readLine();
        IOReader.readInputFileName(inputFileName);
        System.out.println("Enter the destination file name");
        String outputFileName = bf.readLine();
        IOReader.checkOutputFileName(outputFileName, inputFileName);
        ExternalSort.mergeSort(inputFileName, outputFileName);


    }
}

