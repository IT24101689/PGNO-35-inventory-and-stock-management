package OCMS;
import java.util.*;
class DuplicateEnrollmentException extends Exception {
    DuplicateEnrollmentException(String message) {
        super(message);
    }
}
class MaxStudentsExceededException  extends Exception {
    MaxStudentsExceededException(String message) {
        super(message);
    }
}
class ValidationException extends Exception {
    ValidationException(String message) {
        super(message);
    }
}
class NotEnrolledException extends Exception {
    NotEnrolledException(String message) {
        super(message);
    }
}
interface UserInputValidator{
    static boolean isValidId(String id){
        return id.matches("[A-Z]{3}\\d{4}");
    }
    default void requireNonEmpty(String s) throws ValidationException{
        if(s==null || s.isBlank()){
            throw new ValidationException("Required field is empty");
        }
    }
}
abstract class Person{
    String id;
    String name;
    Person(String id, String name){
        this.id = id;
        this.name = name;
    }
    abstract String getRole();
    void display(){
        System.out.println("Name: " + name + " ID: " + id);
    }
}

class Student extends Person{
    private ArrayList<Course> enrolledCourse;
    Student(String id, String name){
        super(id, name);
        enrolledCourse = new ArrayList<>();
    }
    public String getRole(){
        return "student";
    }
    void display(){
        super.display();
    }
}
class Instructor extends Person{
    private ArrayList<Course> teachingCourse;
    Instructor(String id, String name){
        super(id, name);
        teachingCourse = new ArrayList<>();
    }
    public String getRole(){
        return "Instructor";
    }
    public void addCourse(Course course){
        teachingCourse.add(course);
    }
    void display(){
        super.display();
    }
}
interface courseType{
    void enroll(Student student) throws DuplicateEnrollmentException, MaxStudentsExceededException;
}
class Course implements courseType {
    String code;
    String title;
    Instructor instructor;
    ArrayList<Student> students;
    Course(String code, String title, Instructor instructor){
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        instructor.addCourse(this);
        students = new ArrayList<>();
    }
    public void enroll(Student student)throws DuplicateEnrollmentException, MaxStudentsExceededException{
        if(students.contains(student)){
            throw new DuplicateEnrollmentException("already exists in student list");
        }else if(students.size()==30){
            throw new MaxStudentsExceededException("max students exceeded");
        }else{
            students.add(student);
        }
    }
    void display(){
        System.out.println("Code: " + code);
        System.out.println("Title: " + title);

    }
}
public class DemoApp implements UserInputValidator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DemoApp app = new DemoApp(); // for calling requireNonEmpty
        try {
           
            System.out.print("Enter Instructor ID: ");
            String insId = in.nextLine();
            app.requireNonEmpty(insId);
            if (!UserInputValidator.isValidId(insId)) {
                throw new ValidationException("Invalid instructor ID format. Use format: AAA1234");
            }

            System.out.print("Enter Instructor Name: ");
            String insName = in.nextLine();
            app.requireNonEmpty(insName);

            Instructor instructor = new Instructor(insId, insName);


            System.out.print("Enter Course Code: ");
            String courseCode = in.nextLine();
            app.requireNonEmpty(courseCode);

            System.out.print("Enter Course Title: ");
            String courseTitle = in.nextLine();
            app.requireNonEmpty(courseTitle);

            Course course = new Course(courseCode, courseTitle, instructor);


            while (true) {
                System.out.print("Enter Student ID (or type 'exit' to finish): ");
                String studentId = in.nextLine();
                if (studentId.equalsIgnoreCase("exit")) break;
                app.requireNonEmpty(studentId);
                if (!UserInputValidator.isValidId(studentId)) {
                    System.out.println("Invalid student ID. Must be like ABC1234");
                    continue;
                }

                System.out.print("Enter Student Name: ");
                String studentName = in.nextLine();
                app.requireNonEmpty(studentName);

                Student student = new Student(studentId, studentName);

                try {
                    course.enroll(student);
                    System.out.println("Student enrolled successfully.");
                } catch (DuplicateEnrollmentException | MaxStudentsExceededException e) {
                    System.out.println("Enrollment failed: " + e.getMessage());
                }
            }


            System.out.println("ok lets display");
            System.out.print("Instructor: ");
            instructor.display();
            System.out.println("Course Info:");
            course.display();

            if (course.students.isEmpty()) {
                System.out.println("No students enrolled.");
            } else {
                System.out.println("Enrolled Students:");
                for (Student s : course.students) {
                    s.display();
                }
            }

        } catch (ValidationException e) {
            System.out.println("Input error: " + e.getMessage());
        }
    }
}

