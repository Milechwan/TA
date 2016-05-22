Feature: Edit Evaluation
  As a teacher
  I want to edit the given evaluations with respect to various criteria
  So I can fix any errors

#Controller Scenario
Scenario: Edit evaluation of a student
  Given there is a student with the following information: student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I modify the "MANA" evaluation to "MA" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then the system stores the modification in the student with login "ma2"

#Controller Scenario
Scenario: Remove evaluation of a student
  Given there is a student with the following informations: student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I delete the "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then the system deletes the evaluation

#Controller Scenario
Scenario: Edit an evaluation for a non existent one
  Given there is a student with the following informations: student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I modify the "MANA" evaluation to "10" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then the system do nothing

#GUI Scenario
Scenario: Edit an evaluation for a non existent one
  Given I see the student "Marcos Antonio", login "ma2", has a "MANA" evaluation in the criterion "X", from "Prova 2", date "11/11/11"
  When I request the system to modify the evaluation "MANA" to "10" in the criterion "X", from "Prova 2", date "11/11/11", from student "Marcos Antonio", login "ma2"
  Then an error message related to the evaluation appear