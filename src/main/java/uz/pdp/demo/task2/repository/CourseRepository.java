package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    boolean existsByName(String name);


}
