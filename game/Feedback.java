package game;

public class Feedback {

    public String generateFeedback(String guess, String secretWord) {
        guess = guess.toUpperCase();
        secretWord = secretWord.toUpperCase();

        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretWord.charAt(i);

            if (guessChar == secretChar) {
                // Зелёный цвет для правильных букв на правильных позициях
                feedback.append("\u001B[32m").append(guessChar).append("\u001B[0m");
            } else if (secretWord.indexOf(guessChar) != -1) {
                // Жёлтый цвет для правильных букв на неправильных позициях
                feedback.append("\u001B[33m").append(guessChar).append("\u001B[0m");
            } else {
                // Белый цвет для неправильных букв
                feedback.append("\u001B[37m").append(guessChar).append("\u001B[0m");
            }
        }
        //System.out.println("Generated feedback: " + feedback.toString()); // Отладочный вывод
        return feedback.toString();
    }

}
