package game;

public class Feedback {

    public String generateFeedback(String guess, String secretWord) {
        guess = guess.toUpperCase();
        secretWord = secretWord.toUpperCase();

        StringBuilder feedback = new StringBuilder();
        boolean[] matched = new boolean[secretWord.length()]; // Массив для отслеживания совпадений букв

        // Первый проход: проверка на полное совпадение символов (зеленый цвет)
        for (int i = 0; i < guess.length(); i++) {
            char guessChar = guess.charAt(i);
            char secretChar = secretWord.charAt(i);

            if (guessChar == secretChar) {
                // Зелёный цвет для правильных букв на правильных позициях
                feedback.append("\u001B[32m").append(guessChar).append("\u001B[0m");
                matched[i] = true; // Отмечаем совпадение
            } else {
                feedback.append(" "); // Временно добавляем пробел для следующего прохода
            }
        }

        // Второй проход: проверка на наличие символов в слове, но на другой позиции (желтый цвет)
        for (int i = 0; i < guess.length(); i++) {
            if (feedback.charAt(i) == ' ') {
                char guessChar = guess.charAt(i);
                boolean found = false;

                for (int j = 0; j < secretWord.length(); j++) {
                    if (!matched[j] && secretWord.charAt(j) == guessChar) {
                        // Жёлтый цвет для правильных букв на неправильных позициях
                        feedback.setCharAt(i, '\u001B');
                        feedback.insert(i + 1, "[33m" + guessChar + "\u001B[0m");
                        matched[j] = true; // Отмечаем, что символ уже использован
                        found = true;
                        break;
                    }
                }

                // Если символ не найден, то это неправильная буква (белый цвет)
                if (!found) {
                    feedback.setCharAt(i, '\u001B');
                    feedback.insert(i + 1, "[37m" + guessChar + "\u001B[0m");
                }
            }
        }

        return feedback.toString();
    }
}
