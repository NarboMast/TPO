package com.example.s31641tpo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {
    private String english;
    private String german;
    private String polish;
    @Id
    private Long id;

    public Entry(String english, String german, String polish){
        this.english = english;
        this.german = german;
        this.polish = polish;
    }

    public Entry() {

    }

    public String getEnglish() {
        return english;
    }

    public String getGerman() {
        return german;
    }

    public String getPolish() {
        return polish;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
