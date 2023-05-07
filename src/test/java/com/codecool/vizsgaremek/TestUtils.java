package com.codecool.vizsgaremek;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class TestUtils {
    private static final String TEST_FILES_LOCATION = "src/test/resources/output/";
    private static final List<String> testFileNames = new ArrayList<>();

    private TestUtils() {}

    public static void makeScreenshot(String title, WebDriver driver) {
        Allure.addAttachment(title, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void writeToFile(String contents, String filename) {
        createOutputFolder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILES_LOCATION + filename))) {
            writer.write(contents);
            testFileNames.add(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFromFile(String filename) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILES_LOCATION + filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }

    public static void deleteTestFiles() {
        for (String testFileName : testFileNames) {
            new File(TEST_FILES_LOCATION + testFileName).delete();
        }

        new File(TEST_FILES_LOCATION).delete();

        testFileNames.clear();
    }

    private static void createOutputFolder() {
        File testOutputFolder = new File(TEST_FILES_LOCATION);

        if (!testOutputFolder.exists()) {
            testOutputFolder.mkdir();
        }
    }
}
