package uz.pdp.demo.task2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task2.service.CourseService;
import uz.pdp.demo.task2.entiry.Course;
import uz.pdp.demo.task2.entiry.Response;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Course course) {
        final Response response = courseService.edit(id, course);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK: HttpStatus.CONFLICT).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody Course course) {
        final Response response = courseService.add(course);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = courseService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final Course course = courseService.getById(id);
        return ResponseEntity.status(course != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(course);
    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<Course> courses = courseService.get();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
}