package com.main.assignment.pages;

import com.main.assignment.utils.ConfigProvider;
import com.main.assignment.utils.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class HomePage extends BasePageObject {
    private static String URL = null;
    private static String SelectBoxShowRowFilter = "(//*[contains(text(),'Show rows')])[1]/following::div[1]";
    private static String NumberOfRows = "//tbody/tr";
    private static String CoockiePopClose = "//div[contains(@class,'cmc-cookie-policy-banner__close')]";

    static {
        try {
            URL = ConfigProvider.getString("URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger log = LogManager.getLogger(HomePage.class);

    public void navigateToHomePage() {
        navigateTo(URL);
        if(getElements(By.xpath(CoockiePopClose)).size()>0)
            clickElement(By.xpath(CoockiePopClose));
    }

    public void selectAOptionforShowRowFilter(String value) {
        clickElement(By.xpath(SelectBoxShowRowFilter));
        clickElement(By.xpath("//button[contains(text(),'" + value + "')]"));
    }

    public void verifyTheNumberOfRows(String noOfRows) {
        WebDriverFactory.getDriver().navigate().refresh();
        List<WebElement> rows = getElements(By.xpath(NumberOfRows));
        Assert.assertEquals(rows.size(), Integer.parseInt(noOfRows));
    }
}
