public class Main {
    private static final Client client = new Client();
    private static final AllStudents allStudents = new AllStudents();

    public static void main(String[] args) {
        int choice = client.prompt();
        while (client.isRunAgain()) {
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> allStudents.showName();
                case 3 -> allStudents.showGrade();
                case 4 -> showStatistics();
            }
            choice = client.prompt();
        }
    }

    private static void addStudent() {
        if (allStudents.size() == 15) {
            System.out.println("Sorry we are full!");
            return;
        }
        Double[] qGrades = new Double[4];
        System.out.print("Input the student's name: ");
        String name = client.gatherInput().trim();
        System.out.print("Input the student's graduation year: ");
        int graduationYear = Integer.parseInt(client.gatherInput());
        for (int i = 0; i < qGrades.length; i++) {
            System.out.print("Enter the grade for quarter " + (i + 1) + ": ");
            double grade = Double.parseDouble(client.gatherInput());
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid Grade.");
                return;
            }
            qGrades[i] = grade;
        }
        allStudents.allStudents[allStudents.size()] = new StudentInfo(name, graduationYear, qGrades);
        System.out.println(name + " was added!");
    }

    private static void showStatistics() {
        System.out.print("Enter for which quarter you want to see statistics on (1-4): ");
        int q = Integer.parseInt(client.gatherInput());
        System.out.println();
        if (q > 4 || q < 1) return;
        try {
            Statistics statistics = new Statistics(allStudents.quarter(q));
            System.out.println(allStudents.isEmpty() ? "There is no mean if there are no Students!" : "The mean grade for quarter " + q + " is: " + statistics.mean());
            System.out.println(allStudents.isEmpty() ? "There is no median if there are no Students!" : "The median grade for quarter " + q + " is: " + statistics.median());
            System.out.println(allStudents.isEmpty() ? "There are no mode(s) if there are no Students!" : "The mode(s) grade(s) for quarter " + q + " are: " + (statistics.modes().size() == 0 ? "none" : statistics.modes().toString().replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")));
            System.out.println(allStudents.isEmpty() ? "There are no standard deviations if there are no Students!" : "The standard deviation from the grade for quarter " + q + " is: " + statistics.standardDeviation());
        } catch (Exception ignored) {
        }

    }
}
