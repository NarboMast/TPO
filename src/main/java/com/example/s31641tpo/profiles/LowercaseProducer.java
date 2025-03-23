package com.example.s31641tpo.profiles;

import com.example.s31641tpo.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("lowercase")
public class LowercaseProducer implements MessageProducer{
    @Override
    public String toString(Entry entry) {
        return "English: " + entry.getEnglish().toLowerCase() + ", German: " + entry.getGerman().toLowerCase() + ", Polish:" + entry.getPolish().toLowerCase();
    }
}
