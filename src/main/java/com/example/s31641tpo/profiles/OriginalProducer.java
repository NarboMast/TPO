package com.example.s31641tpo.profiles;

import com.example.s31641tpo.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("original")
public class OriginalProducer implements MessageProducer{
    @Override
    public String toString(Entry entry) {
        return "English: " + entry.getEnglish() + ", German: " + entry.getGerman() + ", Polish:" + entry.getPolish();
    }
}
