package com.example.s31641tpo.profiles;

import com.example.s31641tpo.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("uppercase")
public class UppercaseProducer implements MessageProducer{
    @Override
    public String toString(Entry entry) {
        return "English: " + entry.getEnglish().toUpperCase() + ", German: " + entry.getGerman().toUpperCase() + ", Polish:" + entry.getPolish().toUpperCase();
    }
}
