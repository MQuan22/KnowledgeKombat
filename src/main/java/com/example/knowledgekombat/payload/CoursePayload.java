package com.example.springsocial.payload;

import com.example.springsocial.model.Category;
import com.example.springsocial.model.Question;
import com.example.springsocial.model.University;

import java.util.ArrayList;
import java.util.List;

public class CoursePayload {
    private int id;
    private boolean status;
    private String Image;
    private String description;
    private University university;
    private Category category;
    private List<Question> questions = new ArrayList<>();

    public CoursePayload(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
