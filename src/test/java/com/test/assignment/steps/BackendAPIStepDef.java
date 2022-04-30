package com.test.assignment.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.assignment.utils.APIOperations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class BackendAPIStepDef {
    APIOperations apiOps = new APIOperations();

    @Given("User get the API token")
    public void user_get_the_API_token() throws IOException {
        apiOps.getToken();
    }

    @Given("User provide the valid {string}")
    public void user_provide_the_valid_URI(String URI) throws IOException {
        apiOps.setValidUrl(URI);
    }

    @Given("User provide the valid {string} param for currency map Api")
    public void user_provide_the_valid_Header_Information(String QueryParam) {
        apiOps.setValidHeaderForCurrencyMap(QueryParam);
    }

    @Given("User provide the valid {string} param for price conversion api")
    public void user_provide_the_valid_Header_Information_for_price_conversion(String QueryParam) {
        apiOps.setValidHeaderForPriceConversion(QueryParam);
    }
    @Given("User provide the valid {string} param for info api")
    public void user_provide_the_valid_Header_Information_for_info(String QueryParam) {
        apiOps.setValidHeaderForInfo(QueryParam);
    }
    @Given("User provide the valid {string} currency")
    public void user_provide_convert_to_currency(String QueryParam) {
        apiOps.setValidHeaderForPriceConversionTo(QueryParam);
    }

    @Given("User provide the valid {string} to be converted")
    public void user_provide_numver_of_currency_to_be_converted(String QueryParam) {
        apiOps.setValidHeaderForPriceConversionTotheNumberOfCurrency(QueryParam);
    }

    @When("User hits {string} api endpoint")
    public void user_hits_CRUD_OPERATION_api_endpoint(String MethodCall) {
        apiOps.triggetApiCall(MethodCall);
    }

    @Then("User is able to verify the {string} code")
    public void user_is_able_to_verify_the_repsonse_code(String code) {
        apiOps.verifyStatusCode(code);
    }

    @Then("User retrieves the ID for provided currencies")
    public void user_retrieves_id_for_currencies() throws IOException {
        apiOps.getIdsForCurrencies();
    }
    @Then("User print the converted Bolivian value on screen")
    public void print_converted_value() throws IOException {
        apiOps.printConvertedValueonScreen();
    }
    @Then("User verify the {string} as {string}")
    public void verify_a_coin_details(String key,String value) throws IOException {
        apiOps.verify_a_coin_details(key,value);
    }
    @Then("User verify and print the cryptocurrencies have mineable tag associated")
    public void verify_print_the_coin() throws IOException {
        apiOps.verifyAndPrintTheCoin();
    }

}
