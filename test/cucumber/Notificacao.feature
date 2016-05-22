Feature: Notification
    As an administrator
    I want to be notified about the students issues without searching for their historic
    So I can know who has its performance under the class evaluations average quickly

#GUI
  Scenario: Be notified about the students's performance issues
    Given there is the performance problem "70% of evaluations are under the class average" with the students Silvio and Joan
    When I log in the software
    Then the item "Reports" on the menu will show that there are new notifications


#GUI
  Scenario: Notify new report type
    Given that "70% of evaluations are MANA" report is in the system
    And I am in the Evaluation page
    When I add the evaluation "MANA" to the student "Joan", login "jsd"
    Then 70% of his evaluations are composed of MANA
    And I should see a new notification related to the new report type

#Controller
  Scenario: Add new report type to the reports list
    Given that "70% of evaluations are MANA" and "70% of evaluations are under the class average" are on the system
    When I add the evaluation "MANA" to the student "Silvio", login "shd2"
    Then 70% of his evaluations are composed of MANA
    And the report "70% of evaluations are MANA" is updated

#GUI
Scenario: Show the students list in the "70% of evaluations are under the class average" report
  Given I am in the Report page
  When I select the "70% of evaluations are under the class average report"
  Then I should see the students list related to the "70% of evaluations are under the class average" report