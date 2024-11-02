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
            if (!fileExists) {
                writer.write("Username,SecretWord,Attempts,Result\n");
            }
            writer.write(String.format("%s,%s,%d,%s\n", userName, secretWord, attempts, result));
        } catch (IOException e) {
            handleFileIssues(e);
        }
    }

    public void printStats(String userName) {
        String fileName = "stats.csv";
        File file = new File(fileName);
        int totalGames = 0;
        int totalAttempts = 0;
        int gamesWon = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(userName)) {
                    totalGames++;
                    String[] parts = line.split(",");
                    totalAttempts += Integer.parseInt(parts[2]);
                    if ("win".equalsIgnoreCase(parts[3])) {
                        gamesWon++;
                    }
                }
            }
            if (totalGames > 0) {
                double averageAttempts = (double) totalAttempts / totalGames;
                System.out.printf("Stats for %s:\n", userName);
                System.out.printf("Games played: %d\n", totalGames);
                System.out.printf("Games won: %d\n", gamesWon);
                System.out.printf("Average attempts per game: %.1f\n", averageAttempts);
            } else {
                System.out.println("No stats available for user: " + userName);
            }
        } catch (IOException e) {
            handleFileIssues(e);
        }
    }

    private void handleFileIssues(IOException e) {
        System.err.println("Error handling stats file: " + e.getMessage());
    }
}
