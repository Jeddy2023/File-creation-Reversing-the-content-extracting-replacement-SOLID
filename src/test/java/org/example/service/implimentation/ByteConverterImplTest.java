package org.example.service.implimentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ByteConverterImplTest {
    private ByteConverterImpl byteConverter;
    private final String testFile = "src/test/resources/testFile.txt";
    private final String outputFile = "src/test/resources/outputFile.txt";

    @BeforeEach
    public void setUp() {
        byteConverter = new ByteConverterImpl();
    }

    @Test
    public void testConvertFileToBytesNormal() throws IOException {
        String data = "Hello, world!";
        Files.write(Paths.get(testFile), data.getBytes());

        byteConverter.convertFileToBytes(testFile, outputFile);

        byte[] bytes = Files.readAllBytes(Paths.get(testFile));
        String expectedContent = Arrays.toString(bytes);
        String actualContent = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testConvertFileToBytesEmptyFile() throws IOException {
        Files.write(Paths.get(testFile), "".getBytes());

        byteConverter.convertFileToBytes(testFile, outputFile);

        String expectedContent = Arrays.toString("".getBytes());
        String actualContent = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertEquals(expectedContent, actualContent);
    }
}