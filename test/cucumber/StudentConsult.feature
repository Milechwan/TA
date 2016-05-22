#João Adherval
Feature: Search and Consult a student
  As a teacher
  I want to search a student and consult that student's information
  So I can see the student's performance and evaluations

#Controller Scenario
  Scenario: Search a student that is registered in the system
    Given the student "Jose Maria" with login "jm" is registered in the system
    When I search for "Jose Maria"
    Then the system will return the information about "Jose Maria"

#Controller Scenario
  Scenario: Search a student that isn't registered in the system
    Given the student "Maria Jose" with login "mj" is not registered in the system
    When I search for "Maria Jose"
    Then the system will not return anything

#GUI Scenario
  Scenario: Consult a student's average evaluation criteria
    Given I'm on the "Alunos" page
    And I see the student "Jose Maria" with login "jm" in the list of students
    When I request the student information
    Then all the student's average evaluation in all criteria will appear in the screen

#GUI Scenario
  Scenario: Consult a non registered student's average evaluation criteria
    Given I'm on the "Alunos" page
    And I do not see the student "Jose Maria" with login "jm" in the list of students
    When I request the student information
    Then a error message will appear in the screen

#GUI Scenario
  Scenario: Search the criteria's average with just a part of the student's name
    Given I'm on the "Alunos" page
    And I see the students "Jose Maria" and "Jose da Silva" in the student list
    When I search for "Jose"
    Then a list with the names of "Jose Maria" and "Jose da Silva" will appear on the screen