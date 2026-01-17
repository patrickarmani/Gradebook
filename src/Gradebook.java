import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.io.*;
import java.util.TreeSet;

// Manages students using a HashMap (ID → Student) for fast lookup operations

public class Gradebook {

    HashMap<String, Student> students;

    public Gradebook() {
        students = new HashMap<String, Student>();
    }

    // Adds the student to the map using the student ID as the key
    public void addStudent(Student student) {
        
        students.put(student.id, student);
    }

    public Student findStudentById(String id) {
                    return students.get(id);
}


    public Student requireStudentById(String id) {
        Student student = findStudentById(id);
        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + id);
        }
        return student;
    }

    public boolean addScoreToStudent(String studentId, double score) {
        Student student = findStudentById(studentId);
        if (student == null) return false;

        student.addScore(score);
        return true;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    
    public TreeSet<Student> getRanking(double passingGrade) {
        Comparator<Student> byAvgDescThenId = new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                double avgA = a.calculateAverage();
                double avgB = b.calculateAverage();

                int cmp = Double.compare(avgB, avgA); 
                if (cmp != 0) return cmp;
                
                return a.id.compareTo(b.id);
            }
        };

        TreeSet<Student> ranking = new TreeSet<Student>(byAvgDescThenId);
        ranking.addAll(students.values());
        return ranking;
    }

    // CSV: id,name,score1,score2,...
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students.values()) {
                StringBuilder line = new StringBuilder();
                line.append(s.id).append(",").append(s.name);

                for (int i = 0; i < s.scores.size(); i++) {
                    line.append(",").append(s.scores.get(i));
                }

                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        students.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();

                Student student = new Student(name, id);

                for (int i = 2; i < parts.length; i++) {
                    String scoreText = parts[i].trim();
                    if (!scoreText.isEmpty()) {
                        double score = Double.parseDouble(scoreText);
                        student.addScore(score);
                    }
                }

                addStudent(student);
                

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid score format in file.");
        }
    }
}

