package org.example.service.implimentation;

import org.example.service.ByteConverter;
import org.example.service.FileComparator;
import org.example.service.FileOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class FileServiceTest {
    private FileOperations fileOperations;
    private FileComparator fileComparator;
    private ByteConverter byteConverter;
    private FileService fileService;

    private final String basePath = "src/test/resources/";
    private final String fileName1 = basePath + "file1.txt";
    private final String fileName2 = basePath + "file2.txt";
    private final String fileName3 = basePath + "file3.txt";
    private final String fileName4 = basePath + "file4.txt";

    @BeforeEach
    public void setUp() throws IOException {
        fileOperations = new FileOperationsImpl();
        fileComparator = new FileComparatorImpl(fileOperations);
        byteConverter = new ByteConverterImpl();
        fileService = new FileService(fileOperations, fileComparator, byteConverter);

        Files.createDirectories(Paths.get(basePath));
        Files.write(Paths.get(fileName1), "Hello, world!".getBytes());
        Files.write(Paths.get(fileName2), "".getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(fileName1));
        Files.deleteIfExists(Paths.get(fileName2));
        Files.deleteIfExists(Paths.get(fileName3));
        Files.deleteIfExists(Paths.get(fileName4));
    }

    @Test
    public void testDisplayFileContent() throws IOException {
        fileService.displayFileContent(fileName1);

        String content = new String(Files.readAllBytes(Paths.get(fileName1)));
        assertThat(content).isEqualTo("Hello, world!");
    }

    @Test
    public void testReverseFileContent() throws IOException {
        fileService.reverseFileContent(fileName1, fileName2);

        String reversedContent = new StringBuilder("Hello, world!").reverse().toString();
        String actualContent = new String(Files.readAllBytes(Paths.get(fileName2)));
        assertThat(actualContent).isEqualTo(reversedContent);
    }

    @Test
    public void testReplaceWordInFile() throws IOException {
        String content = "Hello world this is a test";
        Files.write(Paths.get(fileName1), content.getBytes());

        Scanner scanner = new Scanner("2\nJava");
        fileService.replaceWordInFile(scanner, fileName1);

        String expectedContent = "Hello Java this is a test";
        String actualContent = new String(Files.readAllBytes(Paths.get(fileName1)));
        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @Test
    public void testCompareFiles() throws IOException {
        Files.write(Paths.get(fileName2), "Hello, world!".getBytes());

        boolean result = fileService.compareFiles(fileName1, fileName2);
        assertThat(result).isTrue();
    }

    @Test
    public void testConvertFileToBytes() throws IOException {
        fileService.convertFileToBytes(fileName1, fileName4);

        byte[] bytes = Files.readAllBytes(Paths.get(fileName1));
        String expectedContent = Arrays.toString(bytes);
        String actualContent = new String(Files.readAllBytes(Paths.get(fileName4)));
        assertThat(actualContent).isEqualTo(expectedContent);
    }
}