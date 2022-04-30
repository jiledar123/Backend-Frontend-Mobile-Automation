package com.main.assignment.mobile;

import com.main.assignment.utils.BrowsserStackUtils;
import com.main.assignment.utils.ConfigProvider;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.StartsActivity;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class MobileOperation extends BrowsserStackUtils {

    public void lauchApp(String appUrl) throws IOException, InterruptedException {
        launchApplication(appUrl);
    }

    public void clickElement(String element) {
        switch (element) {
            case "Join meeting":
                AndroidElement joinMeeting = (AndroidElement) new WebDriverWait(driver, 30).until(
                        ExpectedConditions.elementToBeClickable(MobileBy.id("us.zoom.videomeetings:id/btnJoinConf")));
                joinMeeting.click();
                break;
            case "Back to Meeting":
                AndroidElement backToMeeting = (AndroidElement) new WebDriverWait(driver, 30).until(
                        ExpectedConditions.elementToBeClickable(MobileBy.xpath("(//android.widget.Button)[1]")));
                backToMeeting.click();
                break;
            case "Join":
                AndroidElement joinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                        ExpectedConditions.elementToBeClickable(MobileBy.id("us.zoom.videomeetings:id/btnJoin")));
                joinButton.click();
                break;
            default:
                break;
        }
    }

    public void verifyTheButtonIsDisable() {
        AndroidElement joinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/btnJoin")));
        boolean isEnabled = Boolean.parseBoolean(joinButton.getAttribute("enabled"));
        Assert.assertFalse(isEnabled);
    }

    AndroidElement insertTextElement;

    public void enterValue(String meetingid) {
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/edtConfNumber")));
        insertTextElement.sendKeys(meetingid);
    }

    public void verifyTheButtonIsEnabled() {
        AndroidElement joinButton = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("us.zoom.videomeetings:id/btnJoin")));
        boolean isEnabled = Boolean.parseBoolean(joinButton.getAttribute("enabled"));
        Assert.assertTrue(isEnabled);
    }

    public void turnOntheVideoToggle() {
        String id = "us.zoom.videomeetings:id/chkNoVideo";
        scrollByID(id, 1);
        AndroidElement videoToggle = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("us.zoom.videomeetings:id/chkNoVideo")));
        videoToggle.click();
    }

    public void scrollByID(String Id, int index) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"" + Id + "\").instance(" + index + "));"));
        } catch (Exception e) {
        }
    }

    public void putTheAppInBackGround() {
        ((AndroidDriver) driver).runAppInBackground(Duration.ofSeconds(5));
    }

    public void launchTheAppAgainInForeground() {
        ((StartsActivity) driver).currentActivity();
        clickElement("Back to Meeting");
    }

    public void VerifyTheErrorText(String message) {
        AndroidElement errorMsgElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/txtMsg")));
        String errorMsg = errorMsgElement.getAttribute("text");
        Assert.assertTrue(errorMsg.contains(message));
    }

    public void closeTheApp() {
        closeApplication();
    }

    public void swipe() {
        try {
            Dimension size = driver.manage().window().getSize();
            int xAxis = (int) (size.width * 0.6);
            int startY = (int) (size.height * 0.3);
            int endY = (int) (size.height * 0.9);
            new TouchAction((PerformsTouchActions) driver)
                    .press(point(xAxis, startY))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(point(xAxis, endY))
                    .release().perform();
        } catch (Exception e) {
        }
    }

    public void swipeTillLeft() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            swipe();
            Thread.sleep(5000);
            AndroidElement BannerText = (AndroidElement) new WebDriverWait(driver, 10).until(
                    ExpectedConditions.presenceOfElementLocated(MobileBy.id("com.linkedin.android:id/growth_prereg_carousel_item_text")));
            String message = BannerText.getAttribute("text");
            System.out.println("Banner Text :: " + message);
        }
    }

    public void loginToApp() throws IOException {
        AndroidElement backToMeeting = (AndroidElement) new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(MobileBy.id("com.linkedin.android:id/growth_prereg_fragment_login_button")));
        backToMeeting.click();
//        AndroidElement userName = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/edtConfNumber")));
//        userName.sendKeys(ConfigProvider.getString("linkedin_username"));
//        AndroidElement password = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.presenceOfElementLocated(MobileBy.id("us.zoom.videomeetings:id/edtConfNumber")));
//        password.sendKeys(ConfigProvider.getString("linkedin_password"));
//        AndroidElement signMeUp = (AndroidElement) new WebDriverWait(driver, 30).until(
//                ExpectedConditions.elementToBeClickable(MobileBy.id("com.linkedin.android:id/growth_prereg_fragment_login_button")));
//        signMeUp.click();
    }
}
