Feature: LUMA User Login

  Background:
    Given the application is loaded

     # scenario = 4 points
  Scenario Outline: Correct login attempt with different user credentials
    Given the 'SignIn' button is clicked
    And the 'Email' field contains '<email>'
    And the 'Password' field contains '<password>'
    When the 'LogIn' button is clicked
    Then the user is redirected to 'https://magento.softwaretestingboard.com/'
    And the 'Expand Sign Out Menu' button is clicked
    And the 'Sign Out' button is clicked

    Examples:
      | email                      | password   |
      | bilal.sheikh2098@gmail.com | billixd@3  |
      | billi@mailbox.unideb.hu    | billixd@#3 |

    # 4 points
  Scenario Outline: Attempting to log in with incorrect email
    Given the 'SignIn' button is clicked
    And the 'Email' field contains '<email>'
    And the 'Password' field contains '<password>'
    When the 'LogIn' button is clicked
    Then the '<emailError>' email message is shown
    Examples:
      | email         | password | emailError                                                   |
      |               | 1234     | This is a required field.                                    |
      | invalid@email | 1234     | Please enter a valid email address (Ex: johndoe@domain.com). |


    # 4 points
  Scenario Outline: Leaving password empty in a login attempt with both valid and invalid email
    Given the 'SignIn' button is clicked
    And the 'Email' field contains '<email>'
    And the 'Password' field contains '<password>'
    When the 'LogIn' button is clicked
    Then the '<passwordError>' password message is shown

    Examples:
      | email                           | password  | passwordError                |
      | bilal.sheikh2098@gmail.com      |           | This is a required field.    |
      | billiddsaaf@mailbox.unideb.hu   |           | This is a required field.    |

    # 3 points
  Scenario: Login attempts with wrong password input
    Given the 'SignIn' button is clicked
    And the 'Email' field contains 'bilal.sheikh2098@gmail.com'
    And the 'Password' field contains 'billissss'
    And the 'LogIn' button is clicked
    Then the user is redirected to 'https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/'
    Then the 'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.' login message is shown

