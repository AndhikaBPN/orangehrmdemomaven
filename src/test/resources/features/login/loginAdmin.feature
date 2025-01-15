Feature: User Login to Admin Page

  @Positive
  Scenario Outline: User successfully login to Admin Page
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should be logged in to Admin Page

    Examples:
      | username | password |
      | Admin    | admin123 |

  @Negative
  Scenario Outline: User failed login to Admin Page with wrong username
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see alert error "<errMsg>"

    Examples:
      | username | password | errMsg              |
      | admin332 | admin123 | Invalid credentials |

  @Negative
  Scenario Outline: User failed login to Admin Page with wrong password
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see alert error "<errMsg>"

    Examples:
      | username | password | errMsg              |
      | admin332 | admin123 | Invalid credentials |

  @Negative
  Scenario Outline: User failed login to Admin Page with wrong username and password
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see alert error "<errMsg>"

    Examples:
      | username | password | errMsg              |
      | Adminwe  | admin321 | Invalid credentials |

  @Negative
  Scenario Outline: User failed login to Admin Page with empty data
    Given User is on login page
    When User enters username "<username>" and password "<password>"
    And User clicks on Login button
    Then User should see login error

    Examples:
      | username | password |
      |          |          |
