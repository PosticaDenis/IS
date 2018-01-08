package utm.md

class Employee {

    String name
    String email
    String position
    Double salary
    Date created = new Date()

    static constraints = {
        email email: true
    }
}
