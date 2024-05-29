package org.example.service;

import java.io.IOException;

public interface ByteConverter {
    void convertFileToBytes(String inputFile, String outputFile) throws IOException;
}
