import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    // Quiz questions, options, and answers stored in arrays
    private static String[] questions = {
        "Which planet is known as the Red Planet?",
        "What is the capital of France?",
        "Which programming language is known as the backbone of web development?"
    };

    private static String[][] options = {
        {"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"},
        {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
        {"1. Java", "2. C++", "3. Python", "4. JavaScript"}
    };

    private static int[] correctAnswers = {2, 3, 4}; // Correct answer indices for each question
    private static int score = 0;
    private static int timeLimit = 10; // Time limit for each question in seconds
    private static boolean timeUp = false; // Flag to check if time is up

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.length; i++) {
            timeUp = false; // Reset time-up flag
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up! Moving to the next question.");
                }
            };
            timer.schedule(task, timeLimit * 1000); // Set timer for each question

            int userAnswer = getUserAnswer(scanner, i);
            timer.cancel(); // Cancel the timer after user submits or time runs out

            if (!timeUp) {
                if (userAnswer == correctAnswers[i]) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer was " + correctAnswers[i] + ".\n");
                }
            } else {
                System.out.println("You didn't answer in time. The correct answer was " + correctAnswers[i] + ".\n");
            }
        }

        // Display final results
        System.out.println("Quiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.length);
        System.out.println("You answered " + score + " questions correctly and " + (questions.length - score) + " incorrectly.");
        scanner.close();
    }

    private static int getUserAnswer(Scanner scanner, int questionIndex) {
        int answer = -1;
        while (!timeUp) {
            System.out.print("Enter your answer (1-4): ");
            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
                if (answer >= 1 && answer <= 4) {
                    return answer; // Return the valid answer
                } else {
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return answer; // Returns -1 if time runs out
    }
}
