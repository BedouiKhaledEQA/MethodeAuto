package com.MethodeAuto;

import org.bouncycastle.oer.Switch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public static WebDriver driver;
    public static Properties props = new Properties();

    public Base() {
        try {
            File file;
            String envirenment = System.getProperty("env", "local");
            if ("local".equalsIgnoreCase(envirenment)) {
                file = new File("src/main/java/com/MethodeAuto/Properties/Config_Local.Properties");
            } else if ("Prod".equalsIgnoreCase(envirenment)) {
                file = new File("src/main/java/com/MethodeAuto/Properties/Config_Prod.Properties");

            } else {
                throw new RuntimeException("envirenement n'est pas pris en charge !");
            }

            FileInputStream fis = new FileInputStream(file);
            props.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WebDriver luncher() {
        String Browser = System.getProperty("browser", "Chrome");
        switch (Browser) {
            case "Chrome" -> driver = new ChromeDriver();
            case "FireFox" -> driver = new FirefoxDriver();
            case "Edge" -> driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(props.getProperty("url"));
        return driver;
    }
}