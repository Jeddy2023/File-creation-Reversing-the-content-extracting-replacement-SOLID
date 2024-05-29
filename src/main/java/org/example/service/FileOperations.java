package org.example.service;

import java.io.IOException;

public interface FileOperations {
    void writeFile(String fileName, String data) throws IOException;
    String readFile(String fileName) throws IOException;
    void reverseFileContent(String inputFile, String outputFile) throws IOException;
    void replaceWordInFile(String fileName, int position, String newWord) throws IOException;
}
