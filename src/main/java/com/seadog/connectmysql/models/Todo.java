package com.seadog.connectmysql.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private boolean urgent;
    private boolean done;
    private Date dateOfCreation;
    private String content;
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Todo_Assignee",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "assignee_id")
    )
    private Set<Assignee> assignees;

    public Todo() {
        this.dateOfCreation = new Date();
        this.assignees = new HashSet<>();
    }

    public Todo(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<Assignee> assignees) {
        this.assignees = assignees;
    }

    public void deleteAssignee(Assignee assignee) {
        this.assignees.remove(assignee);
    }


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", urgent=" + urgent +
                ", done=" + done +
                ", dateOfCreation=" + dateOfCreation +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", assignees=" + assignees +
                '}';
    }
}