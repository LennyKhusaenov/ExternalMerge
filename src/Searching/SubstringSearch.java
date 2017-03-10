package Searching;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SubstringSearch {


    public static void main(String[] args) throws IOException {
        String fileName = readSourceFileName();
        String subString = readSubString();
        searchBySubstring(fileName, subString);
        String reg = readSubString();
        searchByRegularExpr(fileName,reg);

    }

    private static String readSourceFileName() throws IOException {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String sourceFileName;
        File file = null;
        do {
            System.out.println("Enter the file name");
            sourceFileName = reader.readLine();
            file = new File(sourceFileName);
            if (!file.exists()) {
                System.err.println("file not found!");
            }
        } while (!file.exists());
        return sourceFileName;

    }

    private static void searchBySubstring(String fileName, String subString) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String tmpString;
        long numberStr = 1;
        boolean contains = false;
        while ((tmpString = reader.readLine()) != null) {
            if (tmpString.indexOf(subString) != -1) {
                System.out.println(subString + " found in line № " + numberStr + "////" + tmpString);
                contains = true;
            }
            numberStr++;
        }
        if (contains == false) {
            System.out.println("substring not found");
        }
    }


    private static String readSubString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String subString = "";

        System.out.println("Enter substring or regular expression for searching");
        while (subString.equals("")) {
            subString = reader.readLine();
        }
        return subString;

    }


    private static void searchByRegularExpr( String fileName, String expr)throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String tmpString;
        long numberStr = 1;
        boolean contains = false;
        Pattern pt = Pattern.compile(expr);
        while ((tmpString=reader.readLine())!= null) {
            Matcher mt = pt.matcher(tmpString);
            if (mt.find()) {
                System.out.println(tmpString.substring(mt.start()+1, mt.end())+" "+ numberStr);
                contains = true;
            }
            numberStr++;
        }
            if(!contains)
                System.out.println("Matches not found");


    }
}
