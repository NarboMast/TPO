package com.example.s31641tpo.profiles;

import com.example.s31641tpo.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("lowercase")
public class LowercaseProducer implements MessageProducer{
    @Override
    public String toString(Entry entry) {
        return String.format("Id: %d ,English: %s, German: %s, Polish: %s",
                entry.getId(),
                entry.getEnglish().toLowerCase(),
                entry.getGerman().toLowerCase(),
                entry.getPolish().toLowerCase());
    }
}
