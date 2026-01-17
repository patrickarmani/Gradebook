import java.util.ArrayList;

public class Student {
    String name;
    String id;
    ArrayList<Double> scores;

    // Constructor: creates a student with name and id, and an empty score list
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.scores = new ArrayList<Double>();
    }

    // Adds one score to the student's score list
    public void addScore(double score) {
        scores.add(score);
    }

    // Calculates the average of all scores (expression + loop)
    public double calculateAverage() {
        if (scores.size() == 0) return 0.0;

        double sum = 0.0;
        for (int i = 0; i < scores.size(); i++) {
            sum = sum + scores.get(i);
        }
        return sum / scores.size();
    }
    // new function - Determines if the student is passing based on a given passing grade  

    public String getStatus(double passingGrade) {
        double average = calculateAverage();
        if (average >= passingGrade) {
            return "Approved";
        } else {
            return "Reproved";
        }
    }

    public String getSummary(double passingGrade) {
        double avg = calculateAverage();
        String status = getStatus(passingGrade);
        return "Name: " + name + "\n"
            + "ID: " + id + "\n"
            + "Average: " + avg + "\n"
            + "Status: " + status;

    }

    
}
