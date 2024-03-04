@AddressModule
Feature: Address Module API automation

  Scenario Outline: Verify add new address to the application through API
    Given User add Headers and bearer authorization for accessing address endpoints
    And User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    When User send "POST" request for add new address
    Then User verify the status code is 200
    And User verify the create address response message matches 'Address added successfully'

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address      | address_type |
      | Suresh     | Kumar     | 9876543210 | L&T       |    33 | 3378 |     101 |  600100 | Pallikaranai | HOME         |

  Scenario Outline: Verify update address to the application through API
    Given User add Headers and bearer authorization for accessing address endpoints
    And User add request body for  update address "<address_id>""<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>","<country>","<zipcode>","<address>","<address_type>"
    When User send "PUT" request for update address
    Then User verify the status code is 200
    And User verify the update address response message matches 'Address updated successfully'

    Examples: 
      | address_id | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address      | address_type |
      |      12197 | Suresh     | Kumar     | 9876543210 | L&T       |    33 | 3378 |     101 |  600100 | Pallikaranai | HOME         |

  Scenario Outline: Verify delete address to the application through API
    Given User add Headers and bearer authorization for accessing address endpoints
    And User add request body for delete address "<address_id>"
    When User send "DELETE" request for delete address
    Then User verify the status code is 200
    And User verify the delete address response message matches 'Address deleted successfully'

    Examples: 
      | address_id |
      |      12197 |

  Scenario: Verify get address to the application through API
    Given User add Headers and bearer authorization for accessing address endpoints
    When User send "GET" request for get address
    Then User verify the status code is 200
    And User verify the get address response message matches 'OK'
