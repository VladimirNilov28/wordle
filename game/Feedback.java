package game;

import java.util.HashMap;
import java.util.Map;

public class Feedback {

    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String RESET = "\u001B[0m";
    private String WHITE = "\u001B[37m";

    public String generateFeedback(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();
        Map<Character, Integer> secretWordCharCount = new HashMap<>();

        // Count the occurrences of each character in the secret word
        for (char c : secretWord.toCharArray()) {
            secretWordCharCount.put(c, secretWordCharCount.getOrDefault(c, 0) + 1);
        }

        // First pass: mark correct characters (green)
        for (int i = 0; i < guess.length(); i++) {
            char currentChar = guess.charAt(i);
            if (currentChar == secretWord.charAt(i)) {
                feedback.append(GREEN).append(Character.toUpperCase(currentChar)).append(RESET); // green for correct
                secretWordCharCount.put(currentChar, secretWordCharCount.get(currentChar) - 1);
            }
        }

        // Second pass: mark misplaced and incorrect characters
        for (int i = 0; i < guess.length(); i++) {
            char currentChar = guess.charAt(i);
            if (currentChar == secretWord.charAt(i)) {
                continue; // already marked as correct
            }
            // check if the character is in the word but in the wrong position (yellow)
            else if (secretWord.contains(Character.toString(currentChar)) && secretWordCharCount.get(currentChar) > 0) {
                feedback.append(YELLOW).append(Character.toUpperCase(currentChar)).append(RESET); // yellow for misplaced
                secretWordCharCount.put(currentChar, secretWordCharCount.get(currentChar) - 1);
            }
            // if the character is not in the word (white)
            else {
                feedback.append(WHITE).append(Character.toUpperCase(currentChar)).append(RESET); // white for incorrect
            }
        }

        return feedback.toString();
    }

}
