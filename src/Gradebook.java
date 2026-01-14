import java.util.ArrayList;

public class Gradebook {

    ArrayList<Student> students;

    // Constructor: creates an empty gradebook (no students yet)
    public Gradebook() {
        students = new ArrayList<Student>();
    }

    // Adds a student to the gradebook
    public void addStudent(Student student) {
        students.add(student);
    }

    // Finds a student by id; returns null if not found
    public Student findStudentById(String id) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Adds a score to a student by id; returns true if successful
    public boolean addScoreToStudent(String studentId, double score) {
        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        student.addScore(score);
        return true;
    }
}
