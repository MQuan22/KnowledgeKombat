package com.example.knowledgekombat.model;

import com.example.knowledgekombat.payload.CoursePayload;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    private String name;

    private boolean status;
    @Lob
    private byte[] image;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScoreReport> scoreReports = new ArrayList<>();

    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uni_id",referencedColumnName = "id")
    @JsonIgnore
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category = new Category();

    public Course(){
        questions = new ArrayList<>();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<ScoreReport> getScoreReports() {
        return scoreReports;
    }

    public void setScoreReports(List<ScoreReport> scoreReports) {
        this.scoreReports = scoreReports;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        questions.forEach(question -> question.setCourse(this));
        if(this.questions == null){
            questions = new ArrayList<>();
        }
        this.questions.clear();
        this.questions.addAll(questions);
//        questions.forEach(question -> question.setAnswers(question.getAnswers()));
//        this.questions = questions;
    }
//    public void mapping(CoursePayload request){
//        this.setQuestions(request.getQuestions());
//        this.set
//    }
}
