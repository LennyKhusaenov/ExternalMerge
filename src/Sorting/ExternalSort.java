package Sorting;

import java.io.*;
import java.nio.file.Files;

/**
 * External Sort algorithm
   * first, the source file is divided into 2 files -temp1 and temp2
   * files temp1 and temp2 merge into the resulting file, forming ordered pairs
   * the resulting file is again broken into temp1 and temp2, then merges into this file, etc.
   * Until the file is completely sorted
 */

public class ExternalSort {


    static void mergeSort(String inputName, String outputName) throws IOException {

        long strCount = stringCounter(inputName);
        String part1;
        String part2;
        File out = new File(outputName);
        if (out.exists()) {
            out.delete();
        }
        Files.copy(new File(inputName).toPath(), out.toPath());

        long groupSize = 1;

        while (groupSize < strCount) {
            printGroup(outputName, groupSize);
            PrintWriter fileWriter = new PrintWriter(outputName);
            BufferedReader temp1 = IOReader.readFile("temp1");
            BufferedReader temp2 = IOReader.readFile("temp2");
            part1 = temp1.readLine();
            part2 = temp2.readLine();
            int i, j;
            while (part1 != null && part2 != null) {
                i = 0;
                j = 0;
                while (i < groupSize && j < groupSize && part1 != null && part2 != null) {
                    if (part1.compareTo(part2) < 0) {
                        fileWriter.println(part1);
                        part1 = temp1.readLine();
                        i++;
                    } else {
                        fileWriter.println(part2);
                        part2 = temp2.readLine();
                        j++;
                    }
                }
                while (i < groupSize && part1 != null) {
                    fileWriter.println(part1);
                    part1 = temp1.readLine();
                    i++;
                }
                while (j < groupSize && part2 != null) {
                    fileWriter.println(part2);
                    part2 = temp2.readLine();
                    j++;
                }
            }
            while (part1 != null) {
                fileWriter.println(part1);
                part1 = temp1.readLine();
            }
            while (part2 != null) {
                fileWriter.println(part2);
                part2 = temp2.readLine();
            }
            temp1.close();
            temp2.close();
            fileWriter.close();
            groupSize *= 2;
        }
        IOReader.deleteFile("temp1");
        IOReader.deleteFile("temp2");

    }


    private static long stringCounter(String inputName) throws IOException {
        BufferedReader reader = IOReader.readFile(inputName);
        long count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        reader.close();
        return count;
    }

    private static void printGroup(String fileName, long count) throws IOException {
        BufferedReader reader = IOReader.readFile(fileName);
        PrintWriter file1 = new PrintWriter("temp1");
        PrintWriter file2 = new PrintWriter("temp2");
        String tmp = reader.readLine();
        while (tmp != null) {
            for (int i = 0; i < count; i++) {
                if (tmp != null) {
                    file1.println(tmp);
                    tmp = reader.readLine();
                }
            }
            for (int i = 0; i < count; i++) {
                if (tmp != null) {
                    file2.println(tmp);
                    tmp = reader.readLine();
                }
            }
        }
        file1.close();
        file2.close();
        reader.close();
    }


}