package com.example.s31641tpo.repositories;

import com.example.s31641tpo.Entry;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;
    private final List<Entry> entries;
    private final Random random;

    public EntryRepository(EntityManager entityManager ,ArrayList<Entry> arrayList, Random random) {
        this.entityManager = entityManager;
        this.entries = arrayList;
        this.random = random;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public boolean addEntry(Entry entry) {
        entries.add(entry);
        return true;
    }

    public Entry getRandomEntry(){
        return entries.get(random.nextInt(entries.size()));
    }
}
