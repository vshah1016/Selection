import java.util.Arrays;

public class StudentInfo {
    final String name;
    final int yearOfGraduation;
    final double finalAverage;
    public Double[] quarterAverages;

    public StudentInfo(String name, int yearOfGraduation, Double[] quarterAverages) {
        this.name = name;
        this.yearOfGraduation = yearOfGraduation;
        this.quarterAverages = quarterAverages;
        this.finalAverage = Arrays.stream(quarterAverages).mapToDouble(Double::valueOf).sum() / 4.0;
    }

    public StudentInfo(String name, int yearOfGraduation, double finalAverage) {
        this.name = name;
        this.yearOfGraduation = yearOfGraduation;
        this.finalAverage = finalAverage;
    }
}
