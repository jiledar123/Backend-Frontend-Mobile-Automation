package com.main.assignment.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

import static com.main.assignment.utils.ConfigProvider.getString;

/**
 * The type Browsser stack utils.
 */
public class BrowsserStackUtils {

    /**
     * The constant userName.
     */
    public static String userName;

    /**
     * The constant driver.
     */
    public static RemoteWebDriver driver = null;

    static {
        try {
            userName = getString("browserstack_username");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The constant accessKey.
     */
    public static String accessKey;

    static {
        try {
            accessKey = getString("browserstack_password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch application.
     *
     * @param appUrl the app url
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void launchApplication(String appUrl) throws IOException, InterruptedException {
        DesiredCapabilities caps;
        if (ConfigProvider.getString("mobile.platform").equalsIgnoreCase("android")) {
            caps = new DesiredCapabilities();
            caps.setCapability("device", getString("android_device"));
            caps.setCapability("os_version", getString("android_os_version"));
            caps.setCapability("browserstack.local", true);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("noReset", true);
            caps.setCapability("app", appUrl);
            driver = new AndroidDriver<AndroidElement>(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);
        } else if (System.getProperty("platform").equalsIgnoreCase("iOS")) {
            System.out.println("Currently iOS execution is not supported....");
        } else {
            System.out.println("Current Script only support Android....");
        }
    }

    /**
     * Close application.
     */
    public static void closeApplication() {
        if (driver != null) {
            driver.quit();
        }
    }
}
