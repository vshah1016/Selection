# Algorithm

When reading the requirements for the selection sort I see that I am going to need a few things.

> * `StudentInfo.java`
    >

* Hold student's name, year of graduation, and final average

> * `AllStudents.java`
    >

* `StudentInfo allStudents = new StudentInfo[15];`
  >
* Limit at 15 students

>

For the functionality of the program I see only four options:
> 1. Input new Student
> 2. Print by name (a-z)
> 3. Print by grade (100-0)
> 4. Output Statistics Sheet

The input new Student, I will implement the last.

First, I will implement two and three. Printing by name and printing by grade, that is much easier. I already have a
method that shows the list of students as is. All I need to do is sort the list then present that window!

# Selection Sort

I used the common selection sort code. For ascending, it looked like this (`Strings`):

```java
for (int i = 0; i < size() - 1; i++) {
    int minimum = i;
    for (int j = i + 1; j < size(); j++) if (allStudents[j].name.compareToIgnoreCase(allStudents[minimum].name) < 0) minimum = j;
    swap(i, minimum);
}
```

For descending we used a maximum value instead of minimum and added a check:

```java
for (int i = 0; i < size() - 1; i++){
    int maximum = i;
    for(int j  = i + 1; j < size(); j++) if (allStudents[j].finalAverage > allStudents[maximum].finalAverage) maximum = j;
}
```

# Display GUI using Swing

I used a swing UI builder, and some XML magic to make a `StudentChart` object that I can spawn onto any JFrame. My
implementation is that my `display()` method will just take the current list at whichever state it is at, and throw it
on the GUI I designed.

Implementation:

```java
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
            Integer[] quarterAverage = allStudents[i].quarterAverages;
            boolean isQuarterAverage;
            try {
                isQuarterAverage = quarterAverage[0] != null;
            } catch (Exception e){
                isQuarterAverage = false;
            }
            defaultTableModel.addRow(new String[]{allStudents[i].name, Integer.toString(allStudents[i].yearOfGraduation), Double.toString(allStudents[i].finalAverage), isQuarterAverage ? Integer.toString(quarterAverage[0]) : "", isQuarterAverage ? Integer.toString(quarterAverage[1]) : "", isQuarterAverage ? Integer.toString(quarterAverage[2]) : "", isQuarterAverage ? Integer.toString(quarterAverage[3]) : ""});
        }
        jFrame.setContentPane(studentChart.frame);
        jFrame.pack();
        jFrame.setSize(500, 200);
        jFrame.setVisible(true);
```

