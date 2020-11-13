@Report
Feature:Verify Microsite content

  Scenario: Validate Microsite Data
    Given User have data
    And User access the Microsite
    And User input valid DOB
    Then Validate value from Microsite with data from DB

  @ignore
  Scenario: User input 3 times attempt using invalid DOB
    Given User have data
    And User access the Microsite
    And User input invalid DOB 3 times
    Then User can not access the Microsite

  @ignore
  Scenario: User submit without input sign
    Given User have data
    And User access the Microsite
    And User input valid DOB
    And User submit without input sign
    Then User can not submit the form

  Scenario: User click Tidak Setuju for ASNTBX
    Given User have data
    And User access the Microsite
    And User input valid DOB
    And User click Tidak Setuju
    Then Display pop up warning for ASNTBX
