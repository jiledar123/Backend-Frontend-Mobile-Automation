@MobileTask @Regression
Feature:  Verify the mobile tests for the linked in and ms teams application

  @MobileTask1 @zoom
  Scenario Outline: TC01 Verify the Zoom application functionality
    Given User launch the zoom app using browser stack url
    When User Click on "Join meeting" button
    Then User Verify the Join button is disabled
    When User Enter the meeting "<meeting_id>"
    Then User Verify the Join button is enabled
    And User turn on the video toggle
    When User Click on "Join" button
    And User put the app in background
    And User Launch the app again in foreground
    Then User verify the pop up with message "Invalid meeting ID"
    And User close the app
    Examples:
      | meeting_id |
      | 432432343  |

  @MobileTask2 @LinkedIn
  Scenario Outline: TC02 Verify the LinkedIn mobile application functionality
    Given User launch the linkedin app using browser stack url
    And User swipe till last slide and verify the text
#    And User login to the app
#    When User search the "<search-key>"
#    Then User verify the results contrains "<search-key>"
#    And User open the chat
#    And User click on the Search messages
#    When User select the filter My connections
#    Then User verify the "My connections" text appears beneath the cross mark
#    And User tap on the profile photo
#    When User click on the settings
#    And User scroll down to last
#    Then User Validate the LinkedIn Banner text
    And User close the app
    Examples:
      | search-key |
      |            |
