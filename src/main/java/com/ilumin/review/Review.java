package com.ilumin.review;

import com.ilumin.core.BaseEntity;
import com.ilumin.course.Course;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Review extends BaseEntity {

    private int rating;
    private String description;

    @ManyToOne
    private Course course;

    protected Review() {
        super();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
