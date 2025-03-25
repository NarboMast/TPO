package com.example.s31641tpo.controller;

import com.example.s31641tpo.Entry;
import com.example.s31641tpo.profiles.MessageProducer;
import com.example.s31641tpo.services.FileService;
import org.springframework.stereotype.Controller;

import javax.swing.text.html.Option;
import java.util.Optional;
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
                    4. Find a word from dictionary by id
                    5. Delete a word from dictionary by id
                    6. Update a word
                    7. Exit
                    Your choice is:""");
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
                    System.out.println(optionFindById());
                    break;
                case 5:
                    optionDeleteById();
                    break;
                case 6:
                    optionUpdateAnEntry();
                    break;
                case 7:
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
        fileService
                .getEntryRepository()
                .addEntry(new Entry(fileService.getEntryRepository().getIdOfLastEntry()+1,
                        eng,
                        ger,
                        pol));
        System.out.println("Successfully added word to the dictionary");
    }

    public void displayAllWords(){
        System.out.println("""
                Choose and write the number of the option:
                1. Sort by english words
                2. Sort by german words
                3. Sort by polish words
                Default: id""");

        String word;
        String order;

        word = switch (scanner.nextInt()) {
            case 1 -> "english";
            case 2 -> "german";
            case 3 -> "polish";
            default -> "id";
        };

        System.out.println("""
                Choose and write the number of the option:
                1. Ascending
                2. Descending
                Default: id""");
        order = switch (scanner.nextInt()){
            case 1 -> "asc";
            case 2 -> "desc";
            default -> "acs";
        };

        for(Entry e : fileService.getEntryRepository().getEntries(word, order)){
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

    public Entry castOptionalToEntry(Optional<Entry> entry){
        if(entry.isPresent()){
            return entry.get();
        } else {
            System.out.println("Non-existing entry");
            return null;
        }
    }

    public Optional<Entry> optionFindById(){
        System.out.println("Provide an id you want to find a word dictionary by");
        return fileService
                .getEntryRepository()
                .findById(scanner.nextLong());
    }

    public void optionDeleteById(){
        System.out.println("Provide an id you want to delete a word from dictionary by");
        fileService.getEntryRepository().deleteById(scanner.nextLong());
    }

    public void optionUpdateAnEntry(){
        Entry entry = castOptionalToEntry(optionFindById());
        if(entry == null) return;
        while(true){
            System.out.println("\n" + """
                   Choose and write the number of the option:
                   1. Update the english translation
                   2. Update the german translation
                   3. Update the polish translation
                   4. Exit""");
            int temp = scanner.nextInt();
            if(temp == 4) break;
            System.out.println("Provide a new translation");
            switch (temp){
                case 1:
                    entry.setEnglish(scanner.next());
                    updateWord(entry);
                    break;
                case 2:
                    entry.setGerman(scanner.next());
                    updateWord(entry);
                    break;
                case 3:
                    entry.setPolish(scanner.next());
                    updateWord(entry);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
        System.out.println("Updated entry:\n" + entry);
    }

    public void updateWord(Entry entry){
        try {
            fileService.getEntryRepository().update(entry);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
