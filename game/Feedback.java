package game;

public class Feedback {

    private String GREEN = "\u001B[32m";
    private String YELLOW = "\u001B[33m";
    private String RESET = "\u001B[0m";
    private String WHITE = "\u001B[37m";

    public String generateFeedback(String guess, String secretWord) {
        guess = guess.toUpperCase();
        secretWord = secretWord.toUpperCase();
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretWord.charAt(i);

            if (guessChar == secretChar) {
                // Зелёный цвет для правильных букв на правильных позициях
                feedback.append(GREEN).append(guessChar).append(RESET);
            } else if (secretWord.indexOf(guessChar) != -1) {
                // Жёлтый цвет для правильных букв на неправильных позициях
                feedback.append(YELLOW).append(guessChar).append(RESET);
            } else {
                // Белый цвет для неправильных букв
                feedback.append(WHITE).append(guessChar).append(RESET);
            }
        }
        //System.out.println("Generated feedback: " + feedback.toString()); // Отладочный вывод
        return feedback.toString();
    }

}