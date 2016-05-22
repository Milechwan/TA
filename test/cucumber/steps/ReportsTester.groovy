/**
 * Created by Milena Carneiro on 28/04/2016.
 */
package steps

import functional.pages.ReportsPages.ReportsPage
import functional.pages.StudentPages.StudentPage

//Cenários de controlador
//Adicionar novo tipo de relatório na lista de relatórios
Given(~'^that "([^"]*)" and "([^"]*)" are on the the system$'){
    String report1, String report2 ->
        relat1 = Report.findByName(report1)
        relat2 = Report.findByName(report2)
        assert ReportsDataAndOperations.compatibleTo(relat1,report1)
        assert ReportsDataAndOperations.compatibleTo(relat2,report2)
}
When(~'^I add the evaluation "([^"]*)" to the student "([^"]*)", login "([^"]*)"$'){String arg, String loginA->
   EvaluationsDataAndOperations.createEvaluation(arg, loginA)
}
Then(~'^70% of his evaluations are composed of "([^"]*)"$'){
   String evalType ->  double totalE =
           (ReportsDataAndOperations.countType(evalType)/ReportsDataAndOperations.count())*100
       if(totalE>=70){
           return true
       }
}
And(~'^the report "([^"]*)" is updated$'){String relato1 ->
    relatorio = Report.findByName(relato1)
    ReportsDataAndOperations.update(relatorio)
}

//Cenários de GUI
//Ser notificado sobre os problemas de desempenho dos alunos
Given(~'^there is the performance problem "([^"]*)" with the students "([^"]*)" and "([^"]*)"$'){
    String prob -> report1 = Reports.findByName(prob)
        assert ReportsDataAndOperations.compatibleTo(prob,report1)
}
When(~'^I log in the software$'){
    to LoginPage
    at LoginPage
    pages.add("adm","1234")
    at StudentPage
}
Then(~'^the item "Reports" on the menu will show that there are new notifications$'){
    to ReportsPage
    at ReportsPage
    ReportsPage.showNotifications()
}


