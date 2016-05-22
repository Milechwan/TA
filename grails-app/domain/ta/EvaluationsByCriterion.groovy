package ta
/**
 * Created by dlr4 on 12/05/2016.
 */
class EvaluationsByCriterion {
    Criterion criterion
    List<Evaluation> evaluations = new LinkedList<Evaluation>()

    static constraints = {
        criterion nullable: false
    }

    public void addEvaluation(Evaluation evaluationInstance) {
        this.evaluations.add(evaluationInstance);
    }

    public void deleteEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i < this.evaluations.size(); i++) {
            if (this.evaluations.get(i).compatibleTo(evaluationInstance)) {
                this.evaluations.remove(i)
            }
        }
    }

    public Evaluation findSpecificEvaluation(Evaluation evaluationInstance) {
        for (int i = 0; i <this.evaluations.size(); i++) {
            if (this.evaluations.get(i).compatibleToNoValue(evaluationInstance)) {
                return this.evaluations.get(i)
            }
        }

    }
}