package Sorting;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class IOReader {

    static String readInputFileName(String inputFileName) throws IOException {
        File file = null;
        do {
            file = new File(inputFileName);
            if (!file.exists()) {
                System.err.println("file not found");
            }
        } while (!file.exists());
        return inputFileName;

    }

    static String checkOutputFileName(String outputFileName, String inputFileName) throws IOException {

        boolean correctOutputFileName;
        do {
            correctOutputFileName = !outputFileName.equals(inputFileName);
            if (!correctOutputFileName) {
                System.err.println("The output file must not be the same as the original!");
            }
        } while (!correctOutputFileName);
        return outputFileName;
    }

    static BufferedReader readFile(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);
        return reader;
    }

    static void deleteFile(String fileName) {
        File file = new File(fileName);
        file.deleteOnExit();

    }
}

