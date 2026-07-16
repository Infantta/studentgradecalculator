import java.util.Scanner;

/**
 * Student Grade Calculator
 *
 * This program collects a student's name and three numerical grades,
 * validates each grade, calculates the average, assigns a letter grade,
 * and reports whether the student passed or failed. The user may repeat
 * the process for additional students.
 */
public class StudentGradeCalculator {

    private static final int NUMBER_OF_GRADES = 3;
    private static final double PASSING_GRADE = 60.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueProgram = true;

        System.out.println("Student Grade Calculator");
        System.out.println("------------------------");

        // Repeat the complete calculation until the user chooses to stop.
        while (continueProgram) {
            System.out.print("Enter the student's name: ");
            String studentName = input.nextLine().trim();

            // Prevent an empty student name from being accepted.
            while (studentName.isEmpty()) {
                System.out.print("Name cannot be blank. Enter the student's name: ");
                studentName = input.nextLine().trim();
            }

            double[] grades = new double[NUMBER_OF_GRADES];

            // Collect and validate each grade before storing it.
            for (int i = 0; i < grades.length; i++) {
                grades[i] = readValidGrade(input, i + 1);
            }

            double average = calculateAverage(grades);
            char letterGrade = determineLetterGrade(average);
            String status = average >= PASSING_GRADE ? "Pass" : "Fail";

            // Display a clearly formatted report for the current student.
            System.out.println("\nGrade Report");
            System.out.println("------------");
            System.out.println("Student: " + studentName);
            System.out.printf("Average: %.2f%n", average);
            System.out.println("Letter Grade: " + letterGrade);
            System.out.println("Status: " + status);

            continueProgram = askToContinue(input);
            System.out.println();
        }

        System.out.println("Thank you for using the Student Grade Calculator.");
        input.close();
    }

    /**
     * Reads one grade and keeps asking until the user enters a number
     * between 0 and 100, inclusive.
     */
    private static double readValidGrade(Scanner input, int gradeNumber) {
        while (true) {
            System.out.print("Enter grade " + gradeNumber + " (0-100): ");

            if (input.hasNextDouble()) {
                double grade = input.nextDouble();
                input.nextLine(); // Consume the newline left by nextDouble().

                if (grade >= 0 && grade <= 100) {
                    return grade;
                }

                System.out.println("Invalid grade. Enter a value from 0 through 100.");
            } else {
                System.out.println("Invalid input. Enter a numerical grade.");
                input.nextLine(); // Remove the invalid text from the input stream.
            }
        }
    }

    /** Calculates and returns the arithmetic mean of all grades. */
    private static double calculateAverage(double[] grades) {
        double total = 0;

        for (double grade : grades) {
            total += grade;
        }

        return total / grades.length;
    }

    /** Converts a numerical average into the corresponding letter grade. */
    private static char determineLetterGrade(double average) {
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    /** Asks whether another student should be evaluated. */
    private static boolean askToContinue(Scanner input) {
        while (true) {
            System.out.print("Evaluate another student? (Y/N): ");
            String response = input.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Invalid response. Enter Y or N.");
        }
    }
}
