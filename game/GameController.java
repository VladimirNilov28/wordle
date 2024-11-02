package game;

public class GameController {


    private String result = "";
    public void playGame(String guess, String secretWord) {

        if(isGameWon(guess, secretWord)){
            System.out.println("Congratulations! You've guessed the word correctly.");
            result = "win";
        }
        else {
            provideFeedback(guess, secretWord);
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


    private boolean isGameWon(String guess, String secretWord) {
        return guess.equals(secretWord);
    }

    public String getResult() {
        return result;
    }


}
