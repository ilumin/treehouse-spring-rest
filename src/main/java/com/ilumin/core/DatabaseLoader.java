package com.ilumin.core;

import com.ilumin.course.Course;
import com.ilumin.course.CourseRepository;
import com.ilumin.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("ilumin 101", "http://ilumin-101.com");
        course.addReview(new Review(5, "Yikes"));
        course.addReview(new Review(5, "Yikes Yikes"));
        course.addReview(new Review(5, "Yikes Yikes Yikes"));
        courses.save(course);
    }

}
