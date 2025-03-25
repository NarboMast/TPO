package com.example.s31641tpo.repositories;

import com.example.s31641tpo.Entry;
import com.example.s31641tpo.customExceptions.EntryNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EnumType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;
    private final Random random;

    public EntryRepository(EntityManager entityManager, Random random) {
        this.entityManager = entityManager;
        this.random = random;
    }

    public List<Entry> getEntries() {
        return entityManager
                .createQuery("SELECT e FROM Entry e", Entry.class)
                .getResultList();
    }

    public List<Entry> getEntries(String word, String order) {
        word = switch (word) {
            case "english" -> "english";
            case "german" -> "german";
            case "polish" -> "polish";
            default -> "id";
        };
        order = switch (order){
            case "asc" -> "asc";
            case "desc" -> "desc";
            default -> "acs";
        };
        return entityManager
                .createQuery("SELECT e FROM Entry e order by e." + word + " " + order, Entry.class)
                .getResultList();
    }

    @Transactional
    public void addEntry(Entry entry) {
        entityManager.persist(entry);
    }

    public Long getIdOfLastEntry(){
        return getLastEntry().getId();
    }

    public Entry getLastEntry(){
        return (Entry) entityManager
                .createQuery("SELECT e FROM Entry e ORDER BY e.id DESC")
                .setMaxResults(1)
                .getSingleResult();
    }

    public Entry getRandomEntry(){
        return getEntries().get(random.nextInt(getIdOfLastEntry().intValue()));
    }

    public Optional<Entry> findById(Long id){
        return Optional.ofNullable(entityManager.find(Entry.class, id));
    }

    @Transactional
    public void deleteById(Long id){
        findById(id).ifPresent(entityManager::remove);
    }

    @Transactional
    public Entry update(Entry entry) throws EntryNotFoundException {
        Entry dbEntry = findById(entry.getId())
                .orElseThrow(EntryNotFoundException::new);
        dbEntry.setEnglish(entry.getEnglish());
        dbEntry.setGerman(entry.getGerman());
        dbEntry.setPolish(entry.getPolish());
        return dbEntry;
    }
}
