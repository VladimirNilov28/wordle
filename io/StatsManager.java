package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class StatsManager {

    public void saveStats(String userName, String secretWord, int attempts, String result) {
        String fileName = "stats.csv";
        File file = new File(fileName);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            // Если файл не существует, добавьте заголовок
            if (!fileExists) {
                writer.write("Username,SecretWord,Attempts,Result\n");
            }
            // Запись информации об игре
            writer.write(String.format("%s,%s,%d,%s\n", userName, secretWord, attempts, result));
        } catch (IOException e) {
            handleFileIssues(e);
        }
    }

    public List<String> loadStats() {
        // Заглушка для метода, читающего статистику из файла
        return new ArrayList<>();
    }

    private void handleFileIssues(IOException e) {
        // Вывод сообщения об ошибке работы с файлом
        System.err.println("Ошибка при работе с файлом статистики: " + e.getMessage());
    }
}
