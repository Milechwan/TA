package ta



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EvaluationsByCriterionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond EvaluationsByCriterion.list(params), model:[evaluationsByCriterionInstanceCount: EvaluationsByCriterion.count()]
    }

    def show(EvaluationsByCriterion evaluationsByCriterionInstance) {
        respond evaluationsByCriterionInstance
    }

    def create() {
        respond new EvaluationsByCriterion(params)
    }

    public boolean saveEvaluationsByCriterion(EvaluationsByCriterion evaluationsByCriterion){
        if(EvaluationsByCriterion.findByCriterion(evaluationsByCriterion.criterion) == null){
            evaluationsByCriterion.save flush: true
            return true
        }else{
            return false
        }
    }

    public EvaluationsByCriterion createEvaluationsByCriterion(){
        return new EvaluationsByCriterion(params)
    }

    public EvaluationsByCriterion createAndSaveEvaluationsByCriterion(){
        EvaluationsByCriterion evaluationsByCriterion = new EvaluationsByCriterion(params)
        saveEvaluationsByCriterion(evaluationsByCriterion)
        return evaluationsByCriterion
    }

    @Transactional
    def save(EvaluationsByCriterion evaluationsByCriterionInstance) {
        if (evaluationsByCriterionInstance == null) {
            notFound()
            return
        }

        if (evaluationsByCriterionInstance.hasErrors()) {
            respond evaluationsByCriterionInstance.errors, view:'create'
            return
        }

        evaluationsByCriterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'evaluationsByCriterion.label', default: 'EvaluationsByCriterion'), evaluationsByCriterionInstance.id])
                redirect evaluationsByCriterionInstance
            }
            '*' { respond evaluationsByCriterionInstance, [status: CREATED] }
        }
    }

    def edit(EvaluationsByCriterion evaluationsByCriterionInstance) {
        respond evaluationsByCriterionInstance
    }

    @Transactional
    def update(EvaluationsByCriterion evaluationsByCriterionInstance) {
        if (evaluationsByCriterionInstance == null) {
            notFound()
            return
        }

        if (evaluationsByCriterionInstance.hasErrors()) {
            respond evaluationsByCriterionInstance.errors, view:'edit'
            return
        }

        evaluationsByCriterionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'EvaluationsByCriterion.label', default: 'EvaluationsByCriterion'), evaluationsByCriterionInstance.id])
                redirect evaluationsByCriterionInstance
            }
            '*'{ respond evaluationsByCriterionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(EvaluationsByCriterion evaluationsByCriterionInstance) {

        if (evaluationsByCriterionInstance == null) {
            notFound()
            return
        }

        evaluationsByCriterionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'EvaluationsByCriterion.label', default: 'EvaluationsByCriterion'), evaluationsByCriterionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'evaluationsByCriterion.label', default: 'EvaluationsByCriterion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
