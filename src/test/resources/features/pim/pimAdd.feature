Feature: User Add PIM Data

  @Positive
  Scenario Outline: User Successfully add PIM
    Given User already on PIM page
    When User click add pim button
    And User insert profile "<fileName>"
    And User input employee fullname "<firstName>", "<middleName>", "<lastName>"
    And User input employee id "<employeeId>"
    And User enabled login details
    And User input username "<username>"
    And User change status to "Disabled"
    And User input password "<password>"
    And User input confirm password "<confirm password>"
    And User click save button
    Then User click PIM page
    And User search employee name "<firstName>", "<middleName>", "<lastName>"
    And User click search button
    Then User should see the data exist
      | employeeId   | firstName   | middleName   | lastName   |
      | <employeeId> | <firstName> | <middleName> | <lastName> |

    Examples:
      | fileName    | firstName | middleName | lastName | employeeId | username     | password   | confirm password |
      | profile.png | Archie    | Grey       | Buster   |     099080 | archiebuster | ArcBust123 | ArcBust123       |
