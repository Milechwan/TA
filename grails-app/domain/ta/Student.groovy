package ta

import org.grails.datastore.mapping.query.Query

class Student {
    String name;
    String login;
    List<EvaluationsByCriterion> criterionsAndEvaluations = new LinkedList<EvaluationsByCriterion>()

    static constraints = {
        name blank : false
        login unique : true, blank:false;
        criterionsAndEvaluations nullable : false;
    }

    public void addEvaluation(Evaluation evaluationInstance, String criterionName, String evaluationOrigin){
        if(this.criterionsAndEvaluations != null) {
            for (int i = 0; i < this.criterionsAndEvaluations.size(); i++) {
                if (this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)) {
                    this.criterionsAndEvaluations.get(i).addEvaluation(evaluationInstance);
                    return;
                }
            }
        }
        Criterion criterionCreated = Criterion.findByDescription(criterionName)

        List<Evaluation> evaluationWithCriterion = Evaluation.findAllByCriterion(criterionCreated)
        Evaluation finalEvaluation
        for(int i = 0; i < evaluationWithCriterion.size(); i++){
            if(evaluationWithCriterion.get(i).getOrigin().equals(evaluationOrigin)){
                finalEvaluation = evaluationWithCriterion.get(i)
            }
        }

        def evaluationsByCriterionController = new EvaluationsByCriterionController()
        evaluationsByCriterionController.params << [criterion : criterionCreated]
        EvaluationsByCriterion evaluationsByCriterionCreated = evaluationsByCriterionController.createAndSaveEvaluationsByCriterion()
        evaluationsByCriterionCreated.addEvaluation(/*evaluationInstance*/finalEvaluation)

        this.criterionsAndEvaluations.add(evaluationsByCriterionCreated)

        evaluationsByCriterionController.response.reset()
    }

    public void deleteEvaluation(Evaluation evaluationInstance){
        for(int i = 0; i< this.criterionsAndEvaluations.size(); i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.criterion.description)){
                this.criterionsAndEvaluations.get(i).deleteEvaluation(evaluationInstance);
            }
        }
    }

    public EvaluationsByCriterion findEvaluationByCriterion(String criterionName){
        for(int i =0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)){
                return this.criterionsAndEvaluations.get(i);
            }
        }
        return null
    }

    public boolean evaluationExist(Evaluation evaluationInstance){
        for(int i = 0; i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(evaluationInstance.getCriterion().getDescription())){
                List<Evaluation> evaluationsForThisCriterion = this.criterionsAndEvaluations.get(i).evaluations;
                for(int j=0; j<evaluationsForThisCriterion.size();j++){
                    //if(evaluationsForThisCriterion.compatibleTo(evaluationInstance)){
                    //    return true
                    //}
                }
            }
        }
        return false
    }


    /*private boolean criterionExists(String criterionDescription){
        for(int i=0;i<this.criterionsAndEvaluations.size();i++){
            if(this.criterionsAndEvaluations.get(i).criterion.description.equals(criterionDescription))
        }
    }*/
}
