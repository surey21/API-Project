@ChangeProfilePic
Feature: Change profile pic Module API automation

  Scenario: Verify Change Profile pic to the application through API
    Given User add Headers and bearer authorization for accessing ChangeProfilePic endpoints
    When User add multipart for upload profile pic
    When User send "POST" request for change Profile Pic
    Then User verify the status code is 200
    And User verify the create change profile pic response message matches 'Profile updated Successfully'
