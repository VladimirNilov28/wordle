package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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
        List<String> stats = new ArrayList<>();
        String fileName = "stats.csv";
        File file = new File(fileName);

        if (!file.exists()) {
            return stats;
        }

        try (Scanner scanner = new Scanner(file)) {
            // Пропуск заголовка
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                stats.add(scanner.nextLine());
            }
        } catch (IOException e) {
            handleFileIssues(e);
        }

        return stats;
    }

    public void printStats(String userName) {
        List<String> stats = loadStats();
        int gamesPlayed = 0;
        int gamesWon = 0;
        int totalAttempts = 0;

        for (String stat : stats) {
            String[] data = stat.split(",");
            if (data.length == 4 && data[0].equals(userName)) {
                gamesPlayed++;
                totalAttempts += Integer.parseInt(data[2]);
                if (data[3].equals("win")) {
                    gamesWon++;
                }
            }
        }

        if (gamesPlayed > 0) {
            double averageAttempts = (double) totalAttempts / gamesPlayed;
            System.out.printf("Stats for %s:\n", userName);
            System.out.printf("Games played: %d\n", gamesPlayed);
            System.out.printf("Games won: %d\n", gamesWon);
            System.out.printf("Average attempts per game: %.1f\n", averageAttempts);
        } else {
            System.out.printf("No stats available for %s.\n", userName);
        }
    }

    private void handleFileIssues(IOException e) {
        // Вывод сообщения об ошибке работы с файлом
        System.err.println("Ошибка при работе с файлом статистики: " + e.getMessage());
    }
}
