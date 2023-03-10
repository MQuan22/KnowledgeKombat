package com.example.knowledgekombat.payload;

public class CourseResponse extends CoursePayload{
    private Long id;
    public CourseResponse(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
