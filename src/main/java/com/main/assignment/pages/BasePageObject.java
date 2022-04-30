package com.main.assignment.pages;

import com.main.assignment.utils.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public abstract class BasePageObject {

    protected boolean isElementVisible(final By locator) {
        try {
            waitForPageToLoad(locator);
            final WebElement element = WebDriverFactory.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isDisplayed();
        } catch (final Exception e) {
            // Element not found
            return false;
        }
    }
    protected void clickElement(final By locator) {
        final WebElement element = WebDriverFactory.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
    protected void navigateTo(final String url) {
        WebDriverFactory.getDriver().navigate().to(url);
    }

    protected boolean isElementEnabled(final By locator) {
        try {
            final WebElement element = WebDriverFactory.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isEnabled();
        } catch (final TimeoutException e) {
            // Element is not enabled
            return false;
        }
    }

    protected List<WebElement> getElements(By locator) {
        waitForPageToLoad(locator);
        final List<WebElement> elements = WebDriverFactory.getWebDriverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return elements;
    }

    protected WebElement getElement(By locator) {
        waitForPageToLoad(locator);
        final WebElement element = WebDriverFactory.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }
    protected void waitForPageToLoad(By by) {
        WebDriverFactory.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }
    protected void selectAValueByVisibleText(String by,String value){

    }
}
