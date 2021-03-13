import java.util.Scanner;

public class Client {
    final Scanner scanner = new Scanner(System.in);

    private int answer = 0;

    public String gatherInput() {
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public boolean isRunAgain() {
        return !(answer == 0);
    }

    public int prompt() {
        System.out.println("0. Exit");
        System.out.println("1. Input new Student");
        System.out.println("2. Print by name (A-Z)");
        System.out.println("3. Print by grade (100-0)");
        System.out.println("4. Print Stats Sheet");
        System.out.print("Which one would you like to select? (0-4): ");

        answer = scanner.nextInt();
        if (answer > 4 || answer < 0) {
            answer = 0;
            System.out.println("Invalid, exiting.");
        }
        System.out.println();
        scanner.nextLine();
        return answer;
    }
}