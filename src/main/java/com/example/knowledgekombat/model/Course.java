package com.example.knowledgekombat.model;

import com.example.knowledgekombat.payload.CourseResponse;
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
    private String courseType;
    private int timeLimit;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User_Course> userCourses = new ArrayList<>();

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

    public List<User_Course> getScoreReports() {
        return userCourses;
    }

    public void setScoreReports(List<User_Course> userCourses) {
        this.userCourses = userCourses;
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

    public void setQuestions(List<Question> questionList) {
        questionList.forEach(question -> question.setCourse(this));
        if(this.questions == null){
            questionList = new ArrayList<>();
        }
        this.questions.clear();
        this.questions.addAll(questionList);
//        questions.forEach(question -> question.setAnswers(question.getAnswers()));
//        this.questions = questions;
        System.out.println(this.questions);
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public List<User_Course> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<User_Course> userCourses) {
        this.userCourses = userCourses;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public CourseResponse mapping(){
        CourseResponse response = new CourseResponse();
        response.setName(this.getName());
        response.setCategory(this.getCategory().getName());
        response.setDescription(this.getDescription());
        response.setUniversity(this.getUniversity().getName());
        response.setStatus(this.isStatus());
        response.setQuestions(this.getQuestions());
        response.setId(this.getId());
        return response;
    }
}
