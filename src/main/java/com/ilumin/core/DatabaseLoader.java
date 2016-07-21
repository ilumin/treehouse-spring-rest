package com.ilumin.core;

import com.ilumin.course.Course;
import com.ilumin.course.CourseRepository;
import com.ilumin.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courses;

    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        exampleData();
    }

    private void exampleData() {
        String[] templates = {
                "Yikes %s %d",
                "Yep %s %d",
                "Yeah %s %d"
        };
        String[] buzzworlds = {
                "Spring",
                "Java",
                "Scala"
        };

        List<Course> bunchOfCourse = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzworld = buzzworlds[i % buzzworlds.length];
                    String title = String.format(template, buzzworld, i);
                    Course c = new Course(title, "http://ilumin-101.com");
                    c.addReview(new Review(i % 5, String.format("More %s %s", buzzworld, i)));
                    bunchOfCourse.add(c);
                });
        courses.save(bunchOfCourse);
    }

}
