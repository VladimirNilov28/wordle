package io;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordReader {

    public List<String> readWords(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        List<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        if(!file.exists()) handleFileNotFound();
        while(scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        scanner.close();
        return words;

    }

    private void handleFileNotFound() {
        System.err.println("Wtf? File 'wordle-words.txt' not found in package /resourses ;( ");
        System.out.println("Bro, try make 'wordle-words.txt' in package /resourses and try again. :D ");
    }
}
