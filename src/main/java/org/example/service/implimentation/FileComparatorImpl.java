package org.example.service.implimentation;

import org.example.service.FileComparator;
import org.example.service.FileOperations;

import java.io.IOException;

public class FileComparatorImpl implements FileComparator {
    private final FileOperations fileOperations;

    public FileComparatorImpl(FileOperations fileOperations) {
        this.fileOperations = fileOperations;
    }

    @Override
    public boolean compareFiles(String fileName1, String fileName2) throws IOException {
        String content1 = fileOperations.readFile(fileName1);
        String content2 = fileOperations.readFile(fileName2);
        return content1.equals(content2);
    }
}
