package com.test.assignment.steps;

import com.main.assignment.mobile.MobileOperation;
import com.main.assignment.utils.ConfigProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class MobileStepDef {
    MobileOperation mOps = new MobileOperation();

    @Given("User launch the zoom app using browser stack url")
    public void user_launch_the_app_using() throws IOException, InterruptedException {
        mOps.lauchApp(ConfigProvider.getString("zoom_app_url"));
    }

    @Given("User launch the linkedin app using browser stack url")
    public void user_launch_the_linkedinapp_using() throws IOException, InterruptedException {
        mOps.lauchApp(ConfigProvider.getString("linkedin_app_url"));
    }

    @When("User Click on {string} button")
    public void user_Click_on_Join_meeting_button(String element) {
        mOps.clickElement(element);
    }

    @Then("User Verify the Join button is disabled")
    public void user_Verify_the_Join_button_is_disabled() {
        mOps.verifyTheButtonIsDisable();
    }

    @When("User Enter the meeting {string}")
    public void user_Enter_the_digit_meeting_id(String meetingid) {
        mOps.enterValue(meetingid);
    }

    @Then("User Verify the Join button is enabled")
    public void user_Verify_the_Join_button_is_enabled() {
        mOps.verifyTheButtonIsEnabled();
    }

    @When("User turn on the video toggle")
    public void user_turn_off_the_video_toggle() {
        mOps.turnOntheVideoToggle();
    }

    @When("User put the app in background")
    public void user_put_the_app_in_background() {
        mOps.putTheAppInBackGround();
    }

    @When("User Launch the app again in foreground")
    public void user_Launch_the_app_again_in_foreground() {
        mOps.launchTheAppAgainInForeground();
    }

    @Then("User verify the pop up with message {string}")
    public void user_verify_the_pop_up_with_message(String message) {
        mOps.VerifyTheErrorText(message);
    }

    @Then("User close the app")
    public void user_close_the_app() {
        mOps.closeTheApp();
    }

    @Given("User swipe till last slide and verify the text")
    public void user_swipe_till_last_slide_and_verify_the_text() throws InterruptedException {
        mOps.swipeTillLeft();
    }

    @Given("User login to the app")
    public void user_login_to_the_app() throws IOException {
        mOps.loginToApp();
    }

    @When("User search the {string}")
    public void user_search_the(String string) {
    }

    @Then("User verify the results contrains {string}")
    public void user_verify_the_results_contrains(String string) {
    }

    @Then("User open the chat")
    public void user_open_the_chat() {
    }

    @Then("User click on the Search messages")
    public void user_click_on_the_Search_messages() {
    }

    @When("User select the filter My connections")
    public void user_select_the_filter_My_connections() {
    }

    @Then("User verify the {string} text appears beneath the cross mark")
    public void user_verify_the_text_apprears_beneath_the_cross_mark(String string) {
    }

    @Then("User tap on the profile photo")
    public void user_tap_on_the_profile_photo() {
    }

    @When("User click on the settings")
    public void user_click_on_the_settings() {
    }

    @When("User scroll down to last")
    public void user_scroll_down_to_last() {
    }

    @Then("User Validate the LinkedIn Banner text")
    public void user_Validate_the_LinkedIn_Banner_text() {
    }

}
