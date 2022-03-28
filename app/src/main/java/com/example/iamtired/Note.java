package com.example.iamtired;

import java.util.Objects;

public class Note {
    public String name;
    public String description;
    public String importance;
    public String responsible;
    public String date;
    public Boolean isChecked = false;

    public Note(String name, String description, String importance, String responsible, String date) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.importance = importance;
        this.responsible = responsible;
    }
    public Note(String name, String description, String importance, String responsible, String date, Boolean isChecked) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.importance = importance;
        this.responsible = responsible;
        this.isChecked = isChecked;
    }
    public Note(){
        //don't remove
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", importance='" + importance + '\'' +
                ", responsible='" + responsible + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(name, note.name) && Objects.equals(description, note.description) && Objects.equals(importance, note.importance) && Objects.equals(responsible, note.responsible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, importance, responsible);
    }
}
