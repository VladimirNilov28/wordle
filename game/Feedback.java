package game;

public class Feedback {

    public String generateFeedback(String guess, String secretWord) {
        guess = guess.toUpperCase();
        secretWord = secretWord.toUpperCase();

        StringBuilder feedback = new StringBuilder();
        boolean[] matched = new boolean[secretWord.length()];

        // Первый проход: проверка на полное совпадение символов
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretWord.charAt(i);

            if (guessChar == secretChar) {
                // Зелёный цвет для правильных букв на правильных позициях
                feedback.append("\u001B[32m").append(guessChar).append("\u001B[0m");
                matched[i] = true;
            } else {
                // Пометка о несовпадении
                feedback.append(" ");  // временно добавляем пробел для второго прохода
            }
        }

        // Второй проход: проверка наличия символа в слове, но на другой позиции
        for (int i = 0; i < guess.length(); i++) {
            if (feedback.charAt(i) == ' ') {
                char guessChar = guess.charAt(i);
                boolean foundMatch = false;

                for (int j = 0; j < secretWord.length(); j++) {
                    if (!matched[j] && secretWord.charAt(j) == guessChar) {
                        // Жёлтый цвет для правильных букв на неправильных позициях
                        feedback.setCharAt(i, '\u001B');
                        feedback.append("[33m").append(guessChar).append("\u001B[0m");
                        matched[j] = true;
                        foundMatch = true;
                        break;
                    }
                }

                if (!foundMatch) {
                    // Белый цвет для неправильных букв
                    feedback.setCharAt(i, '\u001B');
                    feedback.append("[37m").append(guessChar).append("\u001B[0m");
                }
            }
        }

        return feedback.toString();
    }

}
