package ta

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StudentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    public static Date formattedDate(String dateInString){
        def formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date date = formatter.parse(dateInString);
        return date;
    }
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Student.list(params), model:[studentInstanceCount: Student.count()]
    }
    public boolean addEvaluationsToAllStudents(String criterionName, Evaluation evaluationInstance){
        for(Student student : Student.findAll()){
            student.addEvaluation(evaluationInstance);
            student.save flush : true
        }
        return true
    }

    public void addEvaluationToStudent(String login){
        def student = Student.findByLogin(login);
        def evaluationInstance = new Evaluation(params);
        student.addEvaluation(evaluationInstance);
        student.save flush : true
    }

    public List<Evaluation> countStudentsEvaluated(String criterionName, String origin, String dateInString){
        List<Evaluation> returningValue;
        def evaluation = new Evaluation(origin,null,this.formattedDate(dateInString),new Criterion(criterionName));
        def students = Student.findAll();
        for(int i =0; i< students.size();i++){
            returningValue.add(students.get(i).findEvaluationByCriterion(evaluation.getCriterion().getDescription()).findSpecificEvaluation(evaluation))
        }
        return returningValue;
    }

    public boolean checkEvaluationsAllStudents(String criterionName, String origin, String dateInString){
       def evaluation = new Evaluation(origin,null,this.formattedDate(dateInString),new Criterion(criterionName));
       List<Student> students = Student.findAll()
       for(int i =0; i<students.size();i++){
           def evCriterion  = students.get(i).findEvaluationByCriterion(criterionName);
           if(evCriterion.findSpecificEvaluation(evaluation) != null){
               return true;
           }else{
               return false
           }
       }
    }

    public boolean updateEvaluation(String studentLogin, String newEvaluation, String criterionName, String evaluationOrigin){
        Student updatedStudent = Student.findByLogin(studentLogin)
        for(int i = 0; i < updatedStudent.criterionsAndEvaluations.size(); i++){
            if(updatedStudent.criterionsAndEvaluations.get(i).getCriterion().getDescription().equals(criterionName)){
                List<Evaluation> evaluationsInCriterion = updatedStudent.criterionsAndEvaluations.get(i).getEvaluations();
                for(int j = 0; j < evaluationsInCriterion.size(); j++){
                    if(evaluationsInCriterion.get(j).getOrigin().equals(evaluationOrigin)){
                        evaluationsInCriterion.get(j).setValue(newEvaluation)
                    }
                }
            }
        }
    }

    public int countAllStudents(){
        return Student.findAll().size();
    }
    /*public boolean saveStudent(Student student){
        if(Student.findByLogin(student.login) ==null){
            student.save flush: true
            return true
        }else{
            return false
        }
    }*/

    public boolean saveStudent() {
        def studentInstance = new Student(params);
        if(Student.findByLogin(studentInstance.login) == null) {
            studentInstance.save flush: true
            return true
        }
        return false
    }

    public void addEvaluation(String studentLogin, String criterionName, String evaluationOrigin){

        Student student = Student.findByLogin(studentLogin)
        student.addEvaluation(null, criterionName, evaluationOrigin)
        student.save flush : true
    }

    /*def addCriterion(Criterion criterionInstance){
        for(Student student : Student.findAll()){
            student.criterions.add(criterionInstance);
            save(student)
        }
    }*/

    public Student createStudent(){
        return new Student(params)
    }

    public Student createAndSaveStudent(){
        Student student = new Student(params)
        saveStudent(student)
        return student
    }

    def show(Student studentInstance) {
        respond studentInstance
    }

    def create() {
        respond new Student(params)
    }

    @Transactional
    def save(Student studentInstance) {
        if (studentInstance == null) {
            notFound()
            return
        }

        if (studentInstance.hasErrors()) {
            respond studentInstance.errors, view:'create'
            return
        }

        studentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
                redirect studentInstance
            }
            '*' { respond studentInstance, [status: CREATED] }
        }
    }

    def edit(Student studentInstance) {
        respond studentInstance
    }

    @Transactional
    def update(Student studentInstance) {
        if (studentInstance == null) {
            notFound()
            return
        }

        if (studentInstance.hasErrors()) {
            respond studentInstance.errors, view:'edit'
            return
        }

        studentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
                redirect studentInstance
            }
            '*'{ respond studentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Student studentInstance) {

        if (studentInstance == null) {
            notFound()
            return
        }

        studentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
