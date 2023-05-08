package com.codecool.vizsgaremek;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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
    private static final List<String> testFileNames = new ArrayList<>();

    private TestUtils() {}

    @Step("Create a screenshot with the title: {0}")
    public static void makeScreenshot(String title, WebDriver driver) {
        Allure.addAttachment(title, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void writeToTestFile(String contents) {
        createOutputFolder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TestConstants.WRITE_TO_FILE_TEST_FOLDER + TestConstants.WRITE_TO_FILE_TEST_FILE_NAME))) {
            writer.write(contents);
            testFileNames.add(TestConstants.WRITE_TO_FILE_TEST_FILE_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFromTestFile() {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(TestConstants.WRITE_TO_FILE_TEST_FOLDER + TestConstants.WRITE_TO_FILE_TEST_FILE_NAME))) {
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
            new File(TestConstants.WRITE_TO_FILE_TEST_FOLDER + testFileName).delete();
        }

        new File(TestConstants.WRITE_TO_FILE_TEST_FOLDER).delete();

        testFileNames.clear();
    }

    private static void createOutputFolder() {
        File testOutputFolder = new File(TestConstants.WRITE_TO_FILE_TEST_FOLDER);

        if (!testOutputFolder.exists()) {
            testOutputFolder.mkdir();
        }
    }
}
