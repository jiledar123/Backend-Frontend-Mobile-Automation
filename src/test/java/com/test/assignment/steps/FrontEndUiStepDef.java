package com.test.assignment.steps;

import com.main.assignment.pages.HomePage;
import com.main.assignment.utils.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FrontEndUiStepDef {
    HomePage homePage = new HomePage();

    @Given("User is on coin market cap home page")
    public void user_is_on_coin_market_cap_home_page() {
        WebDriverFactory.initialize();
        homePage.navigateToHomePage();
    }

    @When("User select {string} from show row filter")
    public void user_select_from_show_row_filter(String string) {
        homePage.selectAOptionforShowRowFilter(string);
    }

    @Then("User verify {string} rows displayed")
    public void user_verify_rows_displayed(String string) {
        homePage.verifyTheNumberOfRows(string);
    }

    @When("User select {string} and {string} show row filter")
    public void user_select_and_show_row_filter(String marketCap, String price) throws InterruptedException {
        homePage.selectFilter(marketCap,price);
    }

    @Then("User verify records displayed on page are correct as per the {string} and {string} filter applied")
    public void user_verify_records_displayed_on_page_are_correct_as_per_the_filter_applied(String marketCap, String price) {
        homePage.verifyFilteredResultOnPage(marketCap,price);
    }

    @And("User click on filter button")
    public void user_click_on_filter() {
        homePage.clickOnFilterButton();
    }


}
