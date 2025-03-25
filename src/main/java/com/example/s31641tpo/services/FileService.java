package com.example.s31641tpo.services;

import com.example.s31641tpo.Entry;
import com.example.s31641tpo.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class FileService {
    @Value("${dictionary}")
    private String filename;
    private static final String COMMA_DELIMITER = ",";
    private final EntryRepository entryRepository;

    public FileService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                entryRepository.addEntry(new Entry(Long.parseLong(values[0], 10), values[1], values[2], values[3]));
            }
        }
    }

    public EntryRepository getEntryRepository(){
        return entryRepository;
    }
}
