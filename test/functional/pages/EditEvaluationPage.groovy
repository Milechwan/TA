package pages

import geb.Page

/**
 * Created by TMB on 22/05/2016.
 */
class EditEvaluationPage extends Page {
    static url = "TA/Evaluations/edit"

    static at = {
        title ==~ /Edit Evaluation/
    }

    def fillEvaluationFromStudentDetails(String studentName, String studentLogin, String criterionName, String evaluationOrigin, String evaluationDate, String oldEvaluation, String newEvaluation){
        $("form").studentName = studentName
        $("form").studentLogin = studentLogin
        $("form").criterionName = criterionName
        $("form").evaluationOrigin = evaluationOrigin
        $("form").evaluationDate = evaluationDate
        $("form").oldEvaluation = oldEvaluation
        $("form").newEvaluation = newEvaluation
    }

    def selectUpdateEvaluationFromStudent(){
        $("input", name: "update").click()
    }
}
