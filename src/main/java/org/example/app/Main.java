package org.example.app;

import org.example.service.ByteConverter;
import org.example.service.FileComparator;
import org.example.service.FileOperations;
import org.example.service.implimentation.ByteConverterImpl;
import org.example.service.implimentation.FileComparatorImpl;
import org.example.service.implimentation.FileOperationsImpl;
import org.example.service.implimentation.FileService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileOperations fileOperations = new FileOperationsImpl();
        FileComparator fileComparator = new FileComparatorImpl(fileOperations);
        ByteConverter byteConverter = new ByteConverterImpl();
        FileService fileService = new FileService(fileOperations, fileComparator, byteConverter);
        Scanner scanner = new Scanner(System.in);

        String basePath = "src/main/resources/";
        String fileName1 = basePath + "file1.txt";
        String fileName2 = basePath + "file2.txt";
        String fileName3 = basePath + "file3.txt";
        String fileName4 = basePath + "file4.txt";

        System.out.println("Enter the content to display in the first file:");
        String inputData = scanner.nextLine();
        try {
            fileOperations.writeFile(fileName1, inputData);
            fileOperations.writeFile(fileName3, inputData);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Display content of the first file");
            System.out.println("2. Reverse content of the first file into the second file");
            System.out.println("3. Replace a word in the first file");
            System.out.println("4. Compare content of the first and second files");
            System.out.println("5. Convert the first file data into byte codes and save in another file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    fileService.displayFileContent(fileName1);
                    break;

                case 2:
                    fileService.reverseFileContent(fileName1, fileName2);
                    break;

                case 3:
                    fileService.replaceWordInFile(scanner, fileName1);
                    break;

                case 4:
                    fileService.compareFiles(fileName1, fileName2);
                    break;

                case 5:
                    fileService.convertFileToBytes(fileName1, fileName4);
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}
