Feature: Admin Page functionality

  @smoke
  Scenario: Verify nav bar items in Admin page
    Given User launches the application
    When User login with username as "Admin" and password as "admin123"
    Then User should see the dashboard
    When User clicks on "Admin" button in sidepanel
    Then User should see nav bar menu items
      | User Management |
      | Job             |
      | Organization    |
      | Qualifications  |
      | Configuration   |
