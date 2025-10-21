Feature: Login Page OrangeHRM

  @smoke
  Scenario: Verify login functionality for valid credentials
    Given User launches the application
    When User login with username as "Admin" and password as "admin123"
    Then User should see the dashboard
    
  @smoke
  Scenario: Verify login functionality for Invalid credentials
    Given User launches the application
    When User login with username as "Admin" and password as "admin"
    Then User should see "Invalid credentials" error message
    
  @smoke
  Scenario: Verify login functionality for Invalid credentials message
    Given User launches the application
    When User login with username as "Admin" and password as "admin"
    Then User should see "Unable to login" error message
    
  @smoke
  Scenario: Verify user not navigated to dashboard page if uses Invalid credentials
    Given User launches the application
    When User login with username as "Admin" and password as "Test"
    Then User should see the dashboard