package game;

public class GameController {

    private String result = "";
    private String remainingLetters = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z";

    public void playGame(String guess, String secretWord) {
        if (isGameWon(guess, secretWord)) {
            result = "win";
        } else {
            provideFeedback(guess, secretWord);
            updateRemainingLetters(guess);
            System.out.println("Remaining letters: " + remainingLetters);
        }
    }

    private void provideFeedback(String guess, String secretWord) {
        Feedback feedback = new Feedback();
        String feedbackResult = feedback.generateFeedback(guess, secretWord);
        if (feedbackResult != null && !feedbackResult.isEmpty()) {
            System.out.println("Feedback: " + feedbackResult);
        } else {
            System.out.println("Feedback is empty. Check the generation process.");
        }
    }

    private void updateRemainingLetters(String guess) {
        for (char c : guess.toUpperCase().toCharArray()) {
            if (remainingLetters.contains(String.valueOf(c))) {
                remainingLetters = remainingLetters.replace(String.valueOf(c), "");
            }
        }
        // Добавляем пробелы между оставшимися буквами
        remainingLetters = String.join(" ", remainingLetters.split(""));
    }


    private boolean isGameWon(String guess, String secretWord) {
        return guess.equalsIgnoreCase(secretWord);
    }

    public String getResult() {
        return result;
    }
}
