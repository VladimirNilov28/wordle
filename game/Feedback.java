package game;

import java.util.HashSet;
import java.util.Set;

public class Feedback {

    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String RESET = "\u001B[0m";
    private String WHITE = "\u001B[37m";

    public String generateFeedback(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();
        Set<Character> currentGuessSet = new HashSet<>();

        for (int i = 0; i < guess.length(); i++) {
            char currentChar = guess.charAt(i);
            currentGuessSet.add(currentChar);

            // check if the character is correct (green)
            if (currentChar == secretWord.charAt(i)) {
                feedback.append(GREEN).append(Character.toUpperCase(currentChar)).append(RESET); // green for correct
            }
            // check if the character is in the word but in the wrong position (yellow)
            else if (secretWord.contains(Character.toString(currentChar))) {
                feedback.append(YELLOW).append(Character.toUpperCase(currentChar)).append(RESET); // yellow for misplaced
            }
            // if the character is not in the word (white)
            else {
                feedback.append(WHITE).append(Character.toUpperCase(currentChar)).append(RESET); // white for incorrect
            }
        }

        return feedback.toString();
    }

}
