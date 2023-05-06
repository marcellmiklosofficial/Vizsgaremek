package com.codecool.vizsgaremek;

import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class TestUtils {
    private TestUtils() {}

    public static void makeScreenshot(String title, WebDriver driver) {
        Allure.addAttachment(title, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void main(String[] args) {
        getMembersTestData();
    }

    public static void getMembersTestData() {
        Map<String, Object> data;

        try {
            data = new JSONObject(Files.readString(Path.of("./src/test/resources/members_test_data.json"))).toMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String key : data.keySet()) {

        }
    }
}
