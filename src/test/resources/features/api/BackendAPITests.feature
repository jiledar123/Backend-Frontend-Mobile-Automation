@BackendTask
Feature:  Back-end tests for the website https://coinmarketcap.com/

  @BackendTask1
  Scenario Outline: TC_01A User Retrieves the ID of bitcoin (BTC), usd tether (USDT), and Ethereum (ETH)
    Given User get the API token
    And User provide the valid "<URI-Endpoint>"
    And User provide the valid "<Query>" param for currency map Api
    When User hits "<CRUD_OPERATION>" api endpoint
    Then User is able to verify the "<response>" code
    And User retrieves the ID for provided currencies
    Examples:
      | URI-Endpoint           | Query        | CRUD_OPERATION | response |
      | /v1/cryptocurrency/map | BTC,USDT,ETH | GET            | 200      |

  @BackendTask1
  Scenario Outline: TC_01B User convert "<crypto-coin>" to Bolivian Boliviano
    Given User get the API token
    And User provide the valid "<URI-Endpoint>"
    And User provide the valid "<crypto-coin>" param for price conversion api
    And User provide the valid "<convert-to-currency>" currency
    And User provide the valid "<amount>" to be converted
    When User hits "<CRUD_OPERATION>" api endpoint
    Then User is able to verify the "<response>" code
    And User print the converted Bolivian value on screen
    Examples:
      | URI-Endpoint               | crypto-coin | convert-to-currency | amount | CRUD_OPERATION | response |
      | /v2/tools/price-conversion | BTC         | BOB                 | 1      | GET            | 200      |
      | /v2/tools/price-conversion | USDT        | BOB                 | 1      | GET            | 200      |
      | /v2/tools/price-conversion | ETH         | BOB                 | 1      | GET            | 200      |

#  For Task2
#  The value attribute technical_doc is not matching with actual result for Ethereum info details
#  Expected as per doc is [https://github.com/thereum/wiki/wiki/White-Paper]
#  Actual as per api response [https://github.com/ethereum/wiki/wiki/White-Paper]
#  Pls note I have corrected it in below scenario assuming its typo error, if not below scenario will fail otherwise

  @BackendTask2
  Scenario Outline: TC_02 Verify and Retrieve the Ethereum (ID 1027) technical documentation website from the cryptocurrency/info call
    Given User get the API token
    And User provide the valid "<URI-Endpoint>"
    And User provide the valid "<id>" param for info api
    When User hits "<CRUD_OPERATION>" api endpoint
    Then User is able to verify the "<response>" code
    And User verify the "logo" as "<logo>"
    And User verify the "technical_doc" as "<technical_doc>"
    And User verify the "symbol" as "<symbol>"
    And User verify the "date_added" as "<date_added>"
    And User verify the "tags" as "<tags>"
    Examples:
      | URI-Endpoint            | id   | CRUD_OPERATION | response | logo                                                         | technical_doc                                       | symbol | date_added               | tags     |
      | /v2/cryptocurrency/info | 1027 | GET            | 200      | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png | [https://github.com/ethereum/wiki/wiki/White-Paper] | ETH    | 2015-08-07T00:00:00.000Z | mineable |

  @BackendTask3
  Scenario Outline: TC_03 Verify and Retrieve the first 10 currencies from the cryptocurrency/info call (ID 1, 2, 3 … 10) have mineable tag associated
    Given User get the API token
    And User provide the valid "<URI-Endpoint>"
    And User provide the valid "<ids>" param for info api
    When User hits "<CRUD_OPERATION>" api endpoint
    Then User is able to verify the "<response>" code
    And User verify and print the cryptocurrencies have mineable tag associated
    Examples:
      | URI-Endpoint            | ids                  | CRUD_OPERATION | response |
      | /v2/cryptocurrency/info | 1,2,3,4,5,6,7,8,9,10 | GET            | 200      |