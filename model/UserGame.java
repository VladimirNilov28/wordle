package model;

public class UserGame {

    private String userName;
    private String secretWord;
    private int attempts;
    private String result;

    public UserGame(String userName, String secretWord, int attempts, String result) {
        // 1. Инициализируйте поля `userName`, `secretWord`, `attempts` и `result`.
        this.userName = userName;
        this.secretWord = secretWord;
        this.attempts = attempts;
        this.result = result;
    }

    public String toCSVFormat() {
        // Возвращение строки в формате CSV
        return String.format("%s,%s,%d,%s", userName, secretWord, attempts, result);
    }
}
