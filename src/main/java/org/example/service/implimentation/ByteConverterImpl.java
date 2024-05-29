package org.example.service.implimentation;

import org.example.service.ByteConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ByteConverterImpl implements ByteConverter {
    @Override
    public void convertFileToBytes(String inputFile, String outputFile) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(inputFile));
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(Arrays.toString(bytes).getBytes());
        }
    }
}
