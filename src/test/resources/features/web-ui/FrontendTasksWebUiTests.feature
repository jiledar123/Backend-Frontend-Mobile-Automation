@FrontendTask @Regression
Feature: Verify the front-end/UI functionality for https://coinmarketcap.com/

  @FrontendTask1
  Scenario Outline: TC01 Verify show row filter functionality on home page
    Given User is on coin market cap home page
    When User select "<show-row-value>" from show row filter
    Then User verify "<show-row-value>" rows displayed
    Examples:
      | show-row-value |
      | 100            |
      | 50             |

  @FrontendTask2
  Scenario Outline: TC02 Verify MarketCap  and Price filter functionality on home page
    Given User is on coin market cap home page
    And User click on filter button
    When User select "<MarketCap-value>" and "<Price-value>" show row filter
    Then User verify records displayed on page are correct as per the "<MarketCap-value>" and "<Price-value>" filter applied
    Examples:
      | MarketCap-value | Price-value  |
      | $1B - $10B      | $101 - $1,000 |