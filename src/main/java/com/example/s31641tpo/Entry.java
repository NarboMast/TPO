package com.example.s31641tpo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Entry {
    private String english;
    private String german;
    private String polish;
    @Id
    private Long id;

    public Entry(Long id ,String english, String german, String polish){
        this.id = id;
        this.english = english;
        this.german = german;
        this.polish = polish;
    }

    public Entry() {

    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english){
        this.english = english;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german){
        this.german = german;
    }

    public String getPolish() {
        return polish;
    }

    public void setPolish(String polish){
        this.polish = polish;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString(){
        return String.format("Id: %d, English: %s, German: %s, Polish: %s",id, english, german, polish);
    }
}
