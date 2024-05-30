package org.example.service.implimentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileOperationsImplTest {
    private FileOperationsImpl fileOperations;
    private final String testFile = "src/test/resources/testFile1.txt";

    @BeforeEach
    public void setUp() {
        fileOperations = new FileOperationsImpl();
    }

    @Test
    public void testWriteFileNormal() throws IOException {
        String data = "Hello, world!";
        fileOperations.writeFile(testFile, data);

        String content = new String(Files.readAllBytes(Paths.get(testFile)));
        assertEquals(data, content);
    }

    @Test
    public void testWriteFileEmptyString() throws IOException {
        String data = "";
        fileOperations.writeFile(testFile, data);

        String content = new String(Files.readAllBytes(Paths.get(testFile)));
        assertEquals(data, content);
    }

    @Test
    public void testReadFileNormal() throws IOException {
        String data = "Hello, world!";
        Files.write(Paths.get(testFile), data.getBytes());

        String content = fileOperations.readFile(testFile);
        assertEquals(data, content);
    }

    @Test
    public void testReadFileEmptyFile() throws IOException {
        Files.write(Paths.get(testFile), "".getBytes());

        String content = fileOperations.readFile(testFile);
        assertEquals("", content);
    }

    @Test
    public void testReverseFileContentNormal() throws IOException {
        String data = "Hello, world!";
        Files.write(Paths.get(testFile), data.getBytes());

        String outputFile = "src/test/resources/testFile2.txt";
        fileOperations.reverseFileContent(testFile, outputFile);

        String reversedContent = new StringBuilder(data).reverse().toString();
        String actualContent = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertEquals(reversedContent, actualContent);
    }

    @Test
    public void testReverseFileContentEmptyFile() throws IOException {
        Files.write(Paths.get(testFile), "".getBytes());

        String outputFile = "src/test/resources/testFile2.txt";
        fileOperations.reverseFileContent(testFile, outputFile);

        String actualContent = new String(Files.readAllBytes(Paths.get(outputFile)));
        assertEquals("", actualContent);
    }

    @Test
    public void testReplaceWordInFileNormal() throws IOException {
        String data = "Hello world this is a test";
        Files.write(Paths.get(testFile), data.getBytes());

        fileOperations.replaceWordInFile(testFile, 2, "Java");

        String expectedContent = "Hello Java this is a test";
        String actualContent = new String(Files.readAllBytes(Paths.get(testFile)));
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testReplaceWordInFileInvalidPosition() {
        assertThrows(IllegalArgumentException.class, () -> {
            fileOperations.replaceWordInFile(testFile, 10, "Java");
        });
    }
}