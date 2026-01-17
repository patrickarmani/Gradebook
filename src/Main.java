import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

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
            System.out.println("4) List all students (HashMap values)");
            System.out.println("5) Show ranking (TreeSet ordered by average score)");
            System.out.println("6) Save to CSV");
            System.out.println("7) Load from CSV");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

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
                // add a try to test the error handling
                case 3: {

                    System.out.print("Student ID: ");
                    String id = scanner.nextLine();

                    try {

                        Student student = gradebook.requireStudentById(id);
                        System.out.println("\n--- Student Report ---");
                        System.out.println(student.getSummary(PASSING_GRADE));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 4: {
                    System.out.println("\n--- All Students ---");
                    ArrayList<Student> all = gradebook.getAllStudents();
                    for (int i = 0; i < all.size(); i++) {
                        Student currentStudent = all.get(i);
                        System.out.println(currentStudent.id + " - " + currentStudent.name);
                    }
                    break;
                }

                case 5: {
                    System.out.println("\n--- Ranking (Top to Bottom) ---");
                    TreeSet<Student> ranking = gradebook.getRanking(PASSING_GRADE);
                    int position = 1;

                    for (Student currentStudent : ranking) {
                        System.out.println(position + ") " + currentStudent.id + " - " + currentStudent.name
                                + " | Avg: " + currentStudent.calculateAverage()
                                + " | " + currentStudent.getStatus(PASSING_GRADE));
                        position++;
                    }
                    break;
                }

                case 6: {
                    System.out.print("Filename (example: gradebook.csv): ");
                    String filename = scanner.nextLine();
                    gradebook.saveToFile(filename);
                    System.out.println("Saved to " + filename);
                    break;
                }

                case 7: {
                    System.out.print("Filename (example: gradebook.csv): ");
                    String filename = scanner.nextLine();
                    gradebook.loadFromFile(filename);
                    System.out.println("Loaded from " + filename);
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
