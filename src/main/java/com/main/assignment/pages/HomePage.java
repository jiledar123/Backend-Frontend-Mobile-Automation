package com.main.assignment.pages;

import com.main.assignment.utils.ConfigProvider;
import com.main.assignment.utils.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * The type Home page.
 */
public class HomePage extends BasePageObject {
    private static String URL = null;
    private static String SelectBoxShowRowFilter = "(//*[contains(text(),'Show rows')])[1]/following::div[1]";
    private static String NumberOfRows = "//tbody/tr";
    private static String CoockiePopClose = "//div[contains(@class,'cmc-cookie-policy-banner__close')]";
    private static String FilterButton = "(//button[contains(text(),'Filters')])[2]";
    private static String MoreFilterButton = "//button[contains(text(),'+')]";
    private static String MoreFilterModal = "//h4[contains(text(),'More Filter')]";
    private static String MarketCapFilterToggle = "//button[text()='Market Cap']";
    private static String PriceFilterToggle = "//button[text()='Price']";
    private static String ApplyFilterButton = "//button[contains(text(),'Apply Filter')]";
    private static String ShowResultsButton = "//button[contains(text(),'Show results')]";
    private static String FilteredRows = "//tbody/tr";

    static {
        try {
            URL = ConfigProvider.getString("URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger log = LogManager.getLogger(HomePage.class);

    /**
     * Navigate to home page.
     */
    public void navigateToHomePage() {
        navigateTo(URL);
        if (getElements(By.xpath(CoockiePopClose)).size() > 0)
            clickElement(By.xpath(CoockiePopClose));
    }

    /**
     * Select a option for show row filter.
     *
     * @param value the value
     */
    public void selectAOptionforShowRowFilter(String value) {
        clickElement(By.xpath(SelectBoxShowRowFilter));
        clickElement(By.xpath("//button[contains(text(),'" + value + "')]"));
    }

    /**
     * Verify the number of rows.
     *
     * @param noOfRows the no of rows
     */
    public void verifyTheNumberOfRows(String noOfRows) {
        WebDriverFactory.getDriver().navigate().refresh();
        List<WebElement> rows = getElements(By.xpath(NumberOfRows));
        Assert.assertEquals(rows.size(), Integer.parseInt(noOfRows));
    }

    /**
     * Click on filter button.
     */
    public void clickOnFilterButton() {
        clickElement(By.xpath(FilterButton));
    }

    /**
     * Select filter.
     *
     * @param marketCap the market cap
     * @param price     the price
     * @throws InterruptedException the interrupted exception
     */
    public void selectFilter(String marketCap, String price) throws InterruptedException {
        if (getElements(By.xpath(MoreFilterButton)).size() > 0) {
            clickElement(By.xpath(MoreFilterButton));
            if (getElements(By.xpath(MoreFilterModal)).size() > 0) {
                clickElement(By.xpath(MarketCapFilterToggle));
                clickElement(By.xpath("//button[text()='" + marketCap + "']"));
                clickElement(By.xpath(ApplyFilterButton));
                clickElement(By.xpath(PriceFilterToggle));
                clickElement(By.xpath("//button[text()='" + price + "']"));
                clickElement(By.xpath(ApplyFilterButton));
                clickElement(By.xpath(ShowResultsButton));
            }
        }
    }

    /**
     * Verify filtered result on page.
     *
     * @param marketCap the market cap
     * @param price     the price
     */
    public void verifyFilteredResultOnPage(String marketCap, String price) {
        String[] marketCaps = marketCap.split(" - ");
        String[] prices = price.split(" - ");
        long expectedMarketCapmin = ((Long.parseLong((marketCaps[0].replace("$", "")).replace("B", ""))) * 2000000000) / 2;
        long expectedMarketCapMax = (Long.parseLong((marketCaps[1].replace("$", "")).replace("B", ""))) * 1000000000;
        long expectedPriceMin = Long.parseLong(prices[0].replace("$", ""));
        long expectedPriceMax = Long.parseLong((prices[1].replace("$", "")).replace(",", ""));
        getFilteredResults();
        System.out.println("Total filtered Results:: " + map.size());
        Set<String> keyset = map.keySet();
        for (String key : keyset) {
            System.out.println("Coin Symbol :: " + key);
            long actualMarketCap = Long.parseLong((map.get(key).get("marketcap").replace("$", "")).replace(",", ""));
            System.out.println("Actual Market Cap :: " + map.get(key).get("marketcap"));
            long actualPrice = (long) Double.parseDouble((map.get(key).get("price").replace("$", "")).replace(",", ""));
            System.out.println("Actual Price :: " + map.get(key).get("price"));
            if ((actualMarketCap <= expectedMarketCapMax && actualMarketCap >= expectedMarketCapmin) && (actualPrice <= expectedPriceMax && actualPrice >= expectedPriceMin)) {
                System.out.println("Verified for :: " + key + " Market Capital is within ::" + marketCap + " and Price range is within ::" + price);
            } else {
                System.out.println("Verification Failed for :: " + key + " Market Capital is within ::" + marketCap + " and Price range is within ::" + price);
                Assert.assertFalse(true);
            }
        }
    }

    private static HashMap<String, HashMap<String, String>> map;

    /**
     * Gets filtered results.
     */
    public void getFilteredResults() {
        List<WebElement> element = null;
        boolean result = false;
        int attempts = 0;
        while (attempts < 5) {
            try {
                element = getElements(By.xpath(FilteredRows));
                result = true;
                break;
            } catch (Exception e) {
            }
            attempts++;
        }
        String CoinPath;
        String PricePath;
        String MarketCapPath;
        map = new HashMap<>();
        for (int i = 0; i < element.size(); i++) {
            HashMap<String, String> columnData = new HashMap<>();
            CoinPath = "//tbody/tr[" + (i + 1) + "]/td[3]/descendant::p[contains(@class,'coin-item-symbol')]";
            PricePath = "//tbody/tr[" + (i + 1) + "]/td[4]/descendant::span";
            MarketCapPath = "//tbody/tr[" + (i + 1) + "]/td[7]/descendant::p";
            String coinSymbol = getElement(By.xpath(CoinPath)).getText().trim();
            String price = getElement(By.xpath(PricePath)).getText().trim();
            String marketCap = getElement(By.xpath(MarketCapPath)).getText().trim();
            columnData.put("price", price);
            columnData.put("marketcap", marketCap);
            map.put(coinSymbol, columnData);
        }
    }
}
