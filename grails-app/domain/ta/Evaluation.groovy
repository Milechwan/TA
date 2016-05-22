package ta

class Evaluation {
    String origin;
    String value;
    Date applicationDate;
    Criterion criterion;
    static constraints = {
        origin inList :["Test","Mini-Test","Form","Final"], blank: false
        value inList :["MA","MPA","MANA","--"], blank :false
        criterion nullable : false
    }

    public boolean compatibleTo(Evaluation evaluationInstance){
        if(this.origin.equals(evaluationInstance.getOrigin()) && this.value.equals(evaluationInstance.getValue()) && this.applicationDate.compareTo(evaluationInstance.getApplicationDate())==0 && this.criterion.getDescription().equals(evaluationInstance.getCriterion().getDescription()))
        {
            return true
        }else {
            return false
        }
    }
    public boolean compatibleToNoValue(Evaluation evaluationInstance){
        if(this.origin.equals(evaluationInstance.getOrigin()) && this.applicationDate.compareTo(evaluationInstance.getApplicationDate())==0 && this.criterion.getDescription().equals(evaluationInstance.getCriterion().getDescription()))
        {
            return true
        }else {
            return false
        }
    }
}
