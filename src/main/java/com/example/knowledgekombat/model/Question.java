package com.example.knowledgekombat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    private String content;

    private String answerOption;

    private String media;

    private Date updatedAt;

    public Question(String content, String answerOption, String media, Date updatedAt, List<Answer> answers) {
        this.content = content;
        this.answerOption = answerOption;
        this.media = media;
        this.updatedAt = updatedAt;
        this.answers = answers;
    }

    @JsonIgnoreProperties("question")
    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(String answerOption) {
        this.answerOption = answerOption;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        answers.forEach(question -> question.setQuestion(this));
        if(this.answers == null){
            answers = new ArrayList<>();
        }
        this.answers.clear();
        this.answers.addAll(answers);
    }

}
