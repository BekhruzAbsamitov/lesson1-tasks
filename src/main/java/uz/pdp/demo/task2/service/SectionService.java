package uz.pdp.demo.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.pdp.demo.task2.entiry.Course;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.Section;
import uz.pdp.demo.task2.payload.SectionDto;
import uz.pdp.demo.task2.repository.CourseRepository;
import uz.pdp.demo.task2.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    final SectionRepository sectionRepository;
    final CourseRepository courseRepository;

    public SectionService(SectionRepository sectionRepository, CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }

    public List<Section> get() {
        return sectionRepository.findAll();
    }

    public Section getById(Integer id) {
        final Optional<Section> optionalSection =
                sectionRepository.findById(id);
        if (optionalSection.isEmpty()) {
            return null;
        }
        return optionalSection.get();
    }

    public Response delete(Integer id) {
        final Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isEmpty()) {
            return new Response("Section not found!", false);
        }
        sectionRepository.deleteById(id);
        return new Response("Deleted!", true);
    }

    public Response add(SectionDto sectionDto) {
        final Optional<Course> optionalCourse = courseRepository.findById(sectionDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new Response("Course not found", false);
        }
        final Course course = optionalCourse.get();
        final boolean existsByNameAndCourse = sectionRepository.existsByNameAndCourse(sectionDto.getName(), course);
        if (existsByNameAndCourse) {
            return new Response("Course already exists", false);
        }
        Section section = new Section();
        section.setCourse(course);
        section.setName(sectionDto.getName());
        section.setName(sectionDto.getName());
        sectionRepository.save(section);
        return new Response("Added!", false);
    }

    public Response edit(Integer id, SectionDto sectionDto) {
        final Optional<Section> optionalSection = sectionRepository.findById(id);

        if (optionalSection.isEmpty()) {
            return new Response("Section not found", false);
        }
        final Section section = optionalSection.get();
        section.setName(sectionDto.getName());
        section.setDescription(sectionDto.getDescription());

        final Optional<Course> optionalCourse = courseRepository.findById(sectionDto.getCourseId());
        if (optionalCourse.isEmpty()) {
            return new Response("Course not found", false);
        }
        section.setCourse(optionalCourse.get());

        sectionRepository.save(section);
        return new Response("Edited", false);
    }

}
