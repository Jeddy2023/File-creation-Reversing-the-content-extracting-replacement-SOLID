package org.example.service.implimentation;

import org.example.service.ByteConverter;
import org.example.service.FileComparator;
import org.example.service.FileOperations;

import java.io.IOException;
import java.util.Scanner;

public class FileService {
    private final FileOperations fileOperations;
    private final FileComparator fileComparator;
    private final ByteConverter byteConverter;

    public FileService(FileOperations fileOperations, FileComparator fileComparator, ByteConverter byteConverter) {
        this.fileOperations = fileOperations;
        this.fileComparator = fileComparator;
        this.byteConverter = byteConverter;
    }

    public void displayFileContent(String fileName) {
        try {
            String content = fileOperations.readFile(fileName);
            System.out.println("Content of the first file:");
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    public void reverseFileContent(String inputFile, String outputFile) {
        try {
            fileOperations.reverseFileContent(inputFile, outputFile);
            System.out.println("Content of the first file has been reversed into the second file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reversing the file content.");
            e.printStackTrace();
        }
    }

    public void replaceWordInFile(Scanner scanner, String fileName) {
        try {
            System.out.println("Enter the position to replace word: ");
            int position = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the new word:");
            String newWord = scanner.nextLine();
            fileOperations.replaceWordInFile(fileName, position, newWord);
            System.out.println("Word replaced successfully in the first file.");
        } catch (IOException e) {
            System.out.println("An error occurred while replacing the word in the file.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void compareFiles(String fileName1, String fileName2) {
        try {
            boolean isSame = fileComparator.compareFiles(fileName1, fileName2);
            System.out.println("Are both files the same? " + isSame);
        } catch (IOException e) {
            System.out.println("An error occurred while comparing the files.");
            e.printStackTrace();
        }
    }

    public void convertFileToBytes(String inputFile, String outputFile) {
        try {
            byteConverter.convertFileToBytes(inputFile, outputFile);
            System.out.println("First file content has been converted to byte codes and saved in another file.");
        } catch (IOException e) {
            System.out.println("An error occurred while converting the file content to bytes.");
            e.printStackTrace();
        }
    }
}
