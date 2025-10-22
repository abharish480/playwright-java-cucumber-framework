Feature: Dashboard Page OrangeHRM

  @smoke
  Scenario: Verify menu available in sidepanel
    Given User launches the application
    When User login with username as "Admin" and password as "admin123"
    Then User should see the dashboard
    Then User validates the below menu is displayed in left side panel
      | Admin       |
      | PIM         |
      | Leave       |
      | Time        |
      | Recruitment |
      | My Info     |
      | Performance |
      | Dashboard   |
      | Directory   |
      | Maintenance |
      | Claim       |
      | Buzz        |

  @smoke
  Scenario: Verify user dropdown menu
    Given User launches the application
    When User login with username as "Admin" and password as "admin123"
    Then User should see the dashboard
    Then User validates the dropdown menu
      | About           |
      | Support         |
      | Change Password |
      | Logout          |

  @smoke @harish
  Scenario: Verify help icon on dashboard page
    Given User launches the application
    When User login with username as "Admin" and password as "admin123"
    Then User should see the dashboard
    When User clicks on Help Icon and navigates to new tab
