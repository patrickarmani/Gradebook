//first class created
public class Student {
    String name;
    String id;
    double[] scores;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.scores = new double[5];
    }

    public void addScore(double score, int index) {
        scores[index] = score;
    }

    public double calculateAverage() {
        double sum = 0;

        for int i = 0; i < scores.length; i++) {
            sum = sum + scores[i];
        }
        return sum / scores.length;
    }


}