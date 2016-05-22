
package steps

import pages.RegisterEvaluationPage
import pages.ShowEvaluationPage
import ta.Evaluation
import ta.EvaluationController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

/*
Given I am on Register evaluation page
When I fill in the field "title" with "Git evaluation"
And I press register button
Then I should see the message "Git evaluation registered"
*/

//Given I am on Register evaluation page
Given (~'^I am on Register evaluation page$') {
	->
	to RegisterEvaluationPage
	at RegisterEvaluationPage

}

//When I fill in the field title with "Refactor evaluation"
When (~'^I fill in the field title with "([^"]*)"$') {
	String text ->

	at RegisterEvaluationPage
	page.fillData(text)
}

//And I press register button
And (~'^I press register button$') { ->

	at RegisterEvaluationPage
	page.click()
}

//Then I should be on show evaluation page
Then (~'^I should be on show evaluation page$') { ->

	at ShowEvaluationPage
}


/*
Given I am on Register evaluation page
When I press "Register" button
Then I should see the message "Campo de título é obrigatório. Nenhuma avaliação foi registrada."
*/

//Given I am on Register evaluation page

//And I press register button

//Then I should stay in register evaluation page
/*Then (~'^I should stay in register evaluation page$') { ->
	at RegisterEvaluationPage
}*/

/*
Given the system has no evaluation entitled "Git evaluation" stored
When I create an evaluation entitled "Git evaluation"
Then the evaluation "Git evaluation" should be stored in the system
*/

// Given the system has no evaluation entitled "Git evaluation" stored
Given (~'^the system has no evaluation entitled "([^"]*)" stored$') { String evaluationTitle ->
	assert Evaluation.findByTitle(evaluationTitle) == null
}

// When I create an evaluation entitled "Git evaluation"
When (~'^I create an evaluation entitled "([^"]*)"$') { String evaluationTitle ->
	def evController = new EvaluationController()
	evController.builder.createEvaluation()
	evController.builder.setEvaluationTitle(evaluationTitle)
	def evaluation = evController.builder.getEvaluation()

	evController.saveEvaluation(evaluation)
}

// Then the evaluation "Git evaluation" should be stored in the system
Then (~'^the evaluation "([^"]*)" should be stored in the system$') { String evaluationTitle ->
	assert Evaluation.findByTitle(evaluationTitle) != null
}

/*
Given the system already has an evaluation entitled "Git evaluation" stored
When I create an evaluation entitled "Git evaluation" with question "How to send files to your repository"
Then no evaluation should be store in the system
*/

def evTitle

// Given the system already has an evaluation entitled "Git evaluation" stored
Given (~'^the system already has an evaluation entitled "([^"]*)" stored$') { String evaluationTitle ->
	evController = new EvaluationController()
	evController.builder.createEvaluation()
	evController.builder.setEvaluationTitle(evaluationTitle)
	expectedEvaluation = evController.builder.getEvaluation()

	evController.saveEvaluation(expectedEvaluation)
	evTitle = evaluationTitle

	assert Evaluation.findByTitle(evaluationTitle) != null
}

// When I create an evaluation entitled "Git evaluation" with question "How to send files to your repository"
When (~'^I create an evaluation entitled "([^"]*)" with question "([^"]*)"$') { String evaluationTitle, evaluationQuestion ->
	evController.builder.createEvaluation()
	evController.builder.setEvaluationTitle(evaluationTitle)
	evController.builder.addEvaluationQuestion(evaluationQuestion)
	def evaluation = evController.builder.getEvaluation()

	evController.saveEvaluation(evaluation)
}

// Then no evaluation should be store in the system
Then (~'^no evaluation should be store in the system$') { ->
	def actualEvaluation = Evaluation.findByTitle(evTitle)
	// apenas a avaliacao anterior deve estar salva
	assert actualEvaluation != null
	assert actualEvaluation.equals(expectedEvaluation)
}