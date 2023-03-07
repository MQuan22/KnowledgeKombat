package com.example.knowledgekombat.payload;

import com.example.knowledgekombat.model.Category;
import com.example.knowledgekombat.model.Question;
import com.example.knowledgekombat.model.Topic;
import com.example.knowledgekombat.model.University;

import java.util.ArrayList;
import java.util.List;

public class CoursePayload {
    private String name;
    private boolean status;
    private String Image;
    private String description;
    private String university;
    private String category;

    private List<Topic> topics = new ArrayList<>();
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
