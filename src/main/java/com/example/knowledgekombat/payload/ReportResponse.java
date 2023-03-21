package com.example.knowledgekombat.payload;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReportResponse {
    private byte[] image;
    private String username;
    private float score;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date date;

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public float getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }
}
