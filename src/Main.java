import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Gradebook gradebook = new Gradebook();
        Scanner scanner = new Scanner(System.in);

        final double PASSING_GRADE = 60.0;
        boolean running = true;

        while (running) {
            System.out.println("\n=== GRADEBOOK MENU ===");
            System.out.println("1) Add student");
            System.out.println("2) Add score to student");
            System.out.println("3) Show student report (average + approved/reproved)");
            System.out.println("4) List all students");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {

                case 1: {
                    System.out.print("Student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();

                    Student student = new Student(name, id);
                    gradebook.addStudent(student);

                    System.out.println("Student added.");
                    break;
                }

                case 2: {
                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Score: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();

                    boolean success = gradebook.addScoreToStudent(id, score);
                    if (success) System.out.println("Score added.");
                    else System.out.println("Student not found.");

                    break;
                }

                case 3: {
                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();

                    Student student = gradebook.findStudentById(id);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    double avg = student.calculateAverage();

                    System.out.println("\n--- Student Report ---");
                    System.out.println("Name: " + student.name);
                    System.out.println("ID: " + student.id);
                    System.out.println("Average: " + avg);

                    // Conditionals: approved/reproved
                    if (avg >= PASSING_GRADE) {
                        System.out.println("Status: APPROVED");
                    } else {
                        System.out.println("Status: REPROVED");
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n--- All Students ---");

                    // Loop to traverse student records
                    for (int i = 0; i < gradebook.students.size(); i++) {
                        Student s = gradebook.students.get(i);
                        System.out.println(s.id + " - " + s.name);
                    }

                    break;
                }

                case 0:
                    running = false;
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
