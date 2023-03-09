package com.example.knowledgekombat.payload;

import com.example.knowledgekombat.model.Question;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

public class CoursePayload {
    private String name;
    private boolean status;
    @Lob
    private byte[] image;
    private String description;
    private String university;
    private String category;

    private List<Question> questions = new ArrayList<>();
    public CoursePayload(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
