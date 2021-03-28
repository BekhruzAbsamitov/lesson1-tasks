package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.Course;
import uz.pdp.demo.task2.entiry.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    boolean existsByNameAndCourse(String name, Course course);
}
