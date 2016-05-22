
Feature AddEvaluation
  As a teacher, I want to evaluate each student by criteria, to show them their progress in class
  Scenario: Add evaluation to a criteria
    Given there are no evaluations to all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016"
    When I want to evaluate all students to the "X" criterion, originated from a "Test" and dated from "28/03/2016".
    Then all the evaluations will be stored in on the "X" criterion history of each student

  Scenario: Add evaluations using incomplete data
    Given there are no evaluations to all students to the "X" criterion,
    When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
    Then all evaluations will not be stored in on the "X" criterion history of each student
  Scenario: Add evaluation more than once with same origin
    Given evaluations for every student on the "X" criteria, originated form "Test" and dated from "28/03/2016" are already in the system
    When I want to add a mark to all students to a the "X" criteria, without a specific origin and dated from "28/03/2016"
    Then all the marks will not be stored in on the "X" criteria's history of each student

  Scenario: Error related to add a repeated evaluation
    Given I am at the "Add concept" screen
    And there already are evaluations for the "X" criteria, originated from "Test" and dated from "24/03/2016" in the system
    When I want to evaluate all students to a the "X" criteria, without a specific origin and dated from "28/03/2016".
    Then an error message related to trying to add a repetead mark will be displayed

  Scenario: Import evaluations
    Given I organized all evaluations for the "X" criteron originated from "Midterm", dated from "31/03/2016" in a spreedsheet
    When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
    Then all the marks will be stored in on the "X" criteria's history of each student


  Scenario: Import repeated evaluations
    Given I organized all evaluations for the "X" criteria originated from "Midterm", dated from "31/03/2016" in a spreedsheet
    And there already are evaluations for the "X" criteria, originated from "Midterm" and dated from "31/03/2016" in the system
    When I want to import all evaluations from the spreedsheet to add to all students "X" criterias history, originated from "Midterm" and dated from "31/03/2016"
    Then all the evaluations will not be stored in on the "X" criteria's history of each student

