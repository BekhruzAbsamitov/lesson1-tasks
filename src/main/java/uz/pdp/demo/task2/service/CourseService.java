package uz.pdp.demo.task2.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task2.entiry.Course;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Response add(Course course) {
        final boolean exists = courseRepository.existsByName(course.getName());
        if (exists) {
            return new Response("Course already exists", false);
        }
        courseRepository.save(course);
        return new Response("Added!", true);
    }

    public Response edit(Integer id, Course course) {
        final Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return new Response("Course not found", false);
        }
        final Course c = optionalCourse.get();
        courseRepository.save(c);
        return new Response("Edited!", true);
    }

    public Course getById(Integer id) {
        final Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            return null;
        }
        return optionalCourse.get();
    }

    public Response delete(Integer id) {
        final boolean exists = courseRepository.existsById(id);
        if (exists) {
            courseRepository.deleteById(id);
            return new Response("Deleted!", true);
        }
        return new Response("Course not found", false);
    }

    public List<Course> get() {
        return courseRepository.findAll();
    }
}