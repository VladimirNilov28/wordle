import game.GameController;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import model.UserGame;
import io.StatsManager;
import java.util.Random;
import io.WordReader;
import java.util.List;

public class WordleGame {

    private final Scanner scanner;
    private final GameController controller;
    private boolean validInput = false;
    private final File file = new File("wordle-words.txt"); // change file location if needed
    private String guess;
    private String userName;
    private int attempts = 0;
    private String result = "";
    private String secretWord;

    public WordleGame(Scanner scanner) {
        this.scanner = scanner;
        this.controller = new GameController();
    }

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("Please provide a number as command line argument.");
            return;
        }

        WordleGame game = new WordleGame(new Scanner(System.in));
        try {
            game.startGame(args);
        } catch (FileNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private void startGame(String[] args) throws FileNotFoundException {
        WordReader reader = new WordReader();
        List<String> wordList = reader.readWords(file.getPath());
        Random rand = new Random();

        try {
            int index = Integer.parseInt(args[0]);
            if (index < 0 || index >= wordList.size()) {
                System.out.println("Invalid word index. Choosing a random word.");
                secretWord = wordList.get(rand.nextInt(wordList.size()));
            } else {
                secretWord = wordList.get(index);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid word index. Choosing a random word.");
            secretWord = wordList.get(rand.nextInt(wordList.size()));
        }

        while (!validInput) {
            System.out.print("Enter your username: ");
            userName = scanner.nextLine();
            if (userName == null || userName.isEmpty()) {
                System.out.println("User name can't be empty. Try again.");
            } else {
                validInput = true;
            }
        }

        System.out.println("Welcome to Wordle! Guess the 5-letter word.");
        while (attempts < 6) {
            validInput = false;
            while (!validInput) {
                System.out.print("Enter your guess:  ");
                guess = scanner.nextLine();
                if (guess == null || guess.isEmpty()) {
                    System.out.println("Guess can't be empty, please try again.");
                } else if (!hasOnlyLowercaseLetters(guess)) {
                    System.out.println("Your guess must only contain lowercase letters.");
                } else if (guess.length() != 5) {
                    System.out.println("Your guess must be exactly 5 letters long.");
                } else if (!wordList.contains(guess)) {
                    System.out.println("Word not in list. Please enter a valid word.");
                } else {
                    validInput = true;
                }
            }

            controller.playGame(guess, secretWord);
            result = controller.getResult();
            attempts++;

            if (result.equals("win")) {
                System.out.println("Congratulations! You've guessed the word correctly.");
                break;
            } else {
                System.out.printf("Attempts remaining: %d\n", 6 - attempts);
            }
        }

        // Проверка и присвоение результата, если игрок не выиграл
        if (!result.equals("win")) {
            result = "loss";
            System.out.println("Game over. The correct word was: " + secretWord);
        }

        // Сохранение результатов игры
        StatsManager statsManager = new StatsManager();
        statsManager.saveStats(userName, secretWord, attempts, result);

        System.out.print("Do you want to see your stats? (yes/no): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            statsManager.printStats(userName);
        }

        System.out.println("Press Enter to exit...");
        scanner.nextLine();
    }

    public static boolean hasOnlyLowercaseLetters(String s) {
        return Pattern.matches("[a-z]+", s);
    }
}
