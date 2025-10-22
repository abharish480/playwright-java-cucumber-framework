package com.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {
    public static void writeEnvDetails() {
        Properties props = new Properties();
        props.setProperty("env", ConfigReader.get("env"));
        props.setProperty("browser", ConfigReader.get("browser"));
        props.setProperty("headless", ConfigReader.get("headless"));
        props.setProperty("tags", ConfigReader.get("tags"));
        props.setProperty("threads", ConfigReader.get("threads"));

        try {
            File resultsDir = new File("allure-results");
            if (!resultsDir.exists()) {
                resultsDir.mkdirs(); // ✅ ensure folder exists
            }

            try (FileOutputStream fos = new FileOutputStream(
                    new File(resultsDir, "environment.properties"))) {
                props.store(fos, "Allure Environment Properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
