package test.cucumber.steps

import ta.Student

/**
 * Created by joao on 03/05/16.
 */

//Controller Scenario
Student estudante;

Given(~'^the student "([^"]*)" with login "([^"]*)" is not registered in the system$'){
    String nome, login ->
        assert Student.findByLogin(login) != null
}

When(~'^I search for "([^"]*)"$'){
    String nome ->
        Student.findByName(nome)
}

Then(~'^the system will return the information about "([^"]*)"$'){
    String nome ->
        estudante  = Student.findByName(nome)
        assert estudante.StudentConsultTestDataAndOperations.compatibleTo(estudante, nome)
}

//GUI Scenario

Given(~'^I am on the "([^"]*)" page$'){
    String pageName->
    to StudentConsultPage
    at StudentConsultPage
}

And(~'^I see the student "([^"]*)" with login "([^"]*)" in the list of students$'){
    String name, login ->
    at StudentConsultPage
        page.findStudent(name, login)
}

When(~'^I request the student information$'){
    ->
    at StudentConsultPage
    page.select()
}

Then(~'^all the student\'s average evaluation in all criteria will appear in the screen$'){
    ->
    at StudentConsultPage
    page.showStudentDetails()
}
