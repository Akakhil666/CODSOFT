import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalRounds = 0;
        int totalScore = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generates a number between 1 and 100
            int attempts = 0;
            int maxAttempts = 5;
            boolean correctGuess = false;
            System.out.println("Round " + (totalRounds + 1));
            System.out.println("You have " + maxAttempts + " attempts to guess the number between 1 and 100.");

            while (attempts < maxAttempts && !correctGuess) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    correctGuess = true;
                    totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Too low! Try again.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            }

            totalRounds++;
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Game Over!");
        System.out.println("You played " + totalRounds + " rounds.");
        System.out.println("Your total score is: " + totalScore);

        scanner.close();
    }
}
