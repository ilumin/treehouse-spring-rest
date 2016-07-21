package com.ilumin.core;

import com.ilumin.course.Course;
import com.ilumin.course.CourseRepository;
import com.ilumin.review.Review;
import com.ilumin.user.User;
import com.ilumin.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {

    private final CourseRepository courses;
    private final UserRepository users;

    @Autowired
    public DatabaseLoader(CourseRepository courses, UserRepository users) {
        this.courses = courses;
        this.users = users;
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
        List<User> peoples = Arrays.asList(
            new User("Teerasak", "Vichadee", "ilumin", "123456", new String[] {"ROLE_USER"}),
            new User("Teerasak 01", "Vichadee 01", "ilumin-01", "123456", new String[] {"ROLE_USER"})
        );
        users.save(peoples);

        List<Course> bunchOfCourse = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzworld = buzzworlds[i % buzzworlds.length];
                    String title = String.format(template, buzzworld, i);
                    Course c = new Course(title, "http://ilumin-101.com");
                    Review review = new Review(i % 5, String.format("More %s %s", buzzworld, i));
                    review.setReviewer(peoples.get(i % peoples.size()));
                    c.addReview(review);
                    bunchOfCourse.add(c);
                });
        courses.save(bunchOfCourse);
    }

}
