package com.example.s31641tpo.controller;

import com.example.s31641tpo.Entry;
import com.example.s31641tpo.profiles.MessageProducer;
import com.example.s31641tpo.services.FileService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class FlashcardsController {
    private final Scanner scanner;
    private final MessageProducer messageProducer;
    private final FileService fileService;

    public FlashcardsController(Scanner scanner, MessageProducer messageProducer, FileService fileService) {
        this.scanner = scanner;
        this.messageProducer = messageProducer;
        this.fileService = fileService;
    }

    public void start(){
        System.out.println("Welcome to the Flashcards!");
        while(true){
            System.out.print("\n" + """
                    Choose and write the number of the option:
                    1. Add a new word
                    2. Display all words from the dictionary
                    3. Test
                    4. Exit
                    Your choice is:
                    """);
            switch (scanner.nextInt()){
                case 1:
                    addWord();
                    break;
                case 2:
                    displayAllWords();
                    break;
                case 3:
                    testGame();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
    }

    public void addWord(){
        System.out.println("Type word in english:");
        String eng = scanner.next();
        System.out.println("Type word in german:");
        String ger = scanner.next();
        System.out.println("Type word in polish:");
        String pol = scanner.next();
        if(fileService.getEntryRepository().addEntry(new Entry(eng,ger,pol))){
            System.out.println("Successfully added word to the dictionary");
        } else {
            System.out.println("Failed to add word to the dictionary");
        }
    }

    public void displayAllWords(){
        for(Entry e : fileService.getEntryRepository().getEntries()){
            System.out.println(messageProducer.toString(e));
        }
        System.out.println("The end of the dictionary");
    }

    public void testGame(){
        System.out.println("Translate the next word to german and polish:");
        Entry temp = fileService.getEntryRepository().getRandomEntry();
        System.out.println("English word: " + temp.getEnglish());
        System.out.println("German translation: ");
        String ger = scanner.next();
        System.out.println("Polish translation: ");
        String pol = scanner.next();

        System.out.println("German: " + ger + " (yours), " + temp.getGerman() + " (expected)" + " (" + isCorrect(ger, temp.getGerman()) + ")");
        System.out.println("Polish: " + pol + " (yours), " + temp.getPolish() + " (expected)" + " (" + isCorrect(pol, temp.getPolish()) + ")");
    }

    public boolean isCorrect(String input, String expected){
        return input.equalsIgnoreCase(expected);
    }
}
