import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.Arrays;
import java.util.Objects;

public class AllStudents {
    public final StudentInfo[] allStudents = new StudentInfo[15];

    public int size() {
        int startToAddIndex = 0;
        for (int i = 0; i < allStudents.length; i++) if (allStudents[i] != null) startToAddIndex = i + 1;
        return startToAddIndex;
    }

    public void swap(int a, int b) {
        StudentInfo temp = allStudents[a];
        allStudents[a] = allStudents[b];
        allStudents[b] = temp;
    }

    private void nameSort() {
        for (int i = 0; i < size() - 2; i++) {
            int minimum = i;
            for (int j = i + 1; j < size(); j++)
                if (allStudents[j].name.compareToIgnoreCase(allStudents[minimum].name) < 0) minimum = j;
            swap(i, minimum);
        }
    }

    private void gradeSort() {
        for (int i = 0; i < size() - 2; i++) {
            int maximum = i;
            for (int j = i + 1; j < size(); j++)
                if (allStudents[j].finalAverage > allStudents[maximum].finalAverage) maximum = j;
            swap(i, maximum);
        }
    }

    private void display() {
        JFrame jFrame = new JFrame();
        StudentChart studentChart = new StudentChart();
        DefaultTableModel defaultTableModel = (DefaultTableModel) studentChart.table.getModel();
        Arrays.stream(new String[]{"Name", "Graduation Year", "Final Grade", "Q1", "Q2", "Q3", "Q4"}).forEach(defaultTableModel::addColumn);
        TableColumnModel tableColumnModel = studentChart.table.getColumnModel();
        tableColumnModel.getColumn(3).setPreferredWidth(30);
        tableColumnModel.getColumn(4).setPreferredWidth(30);
        tableColumnModel.getColumn(5).setPreferredWidth(30);
        tableColumnModel.getColumn(6).setPreferredWidth(30);
        for (int i = 0; i < size(); i++) {
            Double[] quarterAverage = allStudents[i].quarterAverages;
            boolean isQuarterAverage;
            try {
                isQuarterAverage = quarterAverage[0] != null;
            } catch (Exception e) {
                isQuarterAverage = false;
            }
            defaultTableModel.addRow(new String[]{allStudents[i].name, Integer.toString(allStudents[i].yearOfGraduation), Double.toString(allStudents[i].finalAverage), isQuarterAverage ? Double.toString(quarterAverage[0]) : "", isQuarterAverage ? Double.toString(quarterAverage[1]) : "", isQuarterAverage ? Double.toString(quarterAverage[2]) : "", isQuarterAverage ? Double.toString(quarterAverage[3]) : ""});
        }
        jFrame.setContentPane(studentChart.frame);
        jFrame.pack();
        jFrame.setSize(500, 200);
        jFrame.setVisible(true);
    }

    public void showName() {
        nameSort();
        display();
    }

    public void showGrade() {
        gradeSort();
        display();
    }

    public Double[] quarter(int q) {
        return Arrays.stream(allStudents).filter(Objects::nonNull).map(student -> student.quarterAverages[q - 1]).toArray(Double[]::new);
    }

    public boolean isEmpty() {
        try {
            return allStudents[0] == null;
        } catch (Exception e) {
            return false;
        }
    }
}
