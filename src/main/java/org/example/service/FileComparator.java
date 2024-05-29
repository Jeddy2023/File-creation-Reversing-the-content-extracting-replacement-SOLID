package org.example.service;

import java.io.IOException;

public interface FileComparator {
    boolean compareFiles(String fileName1, String fileName2) throws IOException;
}
