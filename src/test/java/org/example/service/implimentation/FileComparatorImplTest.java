package org.example.service.implimentation;

import org.example.service.FileOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileComparatorImplTest {

    private FileOperations fileOperations;
    private FileComparatorImpl fileComparator;

    @BeforeEach
    public void setUp() {
        fileOperations = Mockito.mock(FileOperations.class);
        fileComparator = new FileComparatorImpl(fileOperations);
    }

    @Test
    public void testCompareFilesSameContent() throws IOException {
        String content = "Hello, world!";
        Mockito.when(fileOperations.readFile("src/test/resources/file1.txt")).thenReturn(content);
        Mockito.when(fileOperations.readFile("src/test/resources/file2.txt")).thenReturn(content);

        assertTrue(fileComparator.compareFiles("src/test/resources/file1.txt", "src/test/resources/file2.txt"));
    }

    @Test
    public void testCompareFilesDifferentContent() throws IOException {
        Mockito.when(fileOperations.readFile("src/test/resources/file1.txt")).thenReturn("Hello");
        Mockito.when(fileOperations.readFile("src/test/resources/file2.txt")).thenReturn("World");

        assertFalse(fileComparator.compareFiles("src/test/resources/file1.txt", "src/test/resources/file2.txt"));
    }
}