package uz.pdp.demo.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.demo.task2.entiry.Problem;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.Section;
import uz.pdp.demo.task2.payload.ProblemDto;
import uz.pdp.demo.task2.repository.ProblemRepository;
import uz.pdp.demo.task2.repository.SectionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    final ProblemRepository problemRepository;
    final SectionRepository sectionRepository;

    public ProblemService(ProblemRepository problemRepository, SectionRepository sectionRepository) {
        this.problemRepository = problemRepository;
        this.sectionRepository = sectionRepository;
    }

    public Problem getById(Integer id) {
        final Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isEmpty()) {
            return null;
        }
        return optionalProblem.get();
    }

    public List<Problem> get() {
        return problemRepository.findAll();
    }

    public Response delete(Integer id) {
        final Optional<Problem> optionalProblem = problemRepository.findById(id);
        if (optionalProblem.isEmpty()) {
            return new Response("Problem not found", false);
        }
        problemRepository.deleteById(id);
        return new Response("Deleted", true);
    }

    public Response add(ProblemDto problemDto) {
        Problem problem = new Problem();
        problem.setProblem(problemDto.getProblem());
        problem.setBody(problemDto.getBody());
        problem.setSolution(problemDto.getSolution());
        problem.setTitle(problemDto.getTitle());

        final Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());
        optionalSection.ifPresent(problem::setSection);

        problemRepository.save(problem);
        return new Response("Added!", true);
    }

    public Response edit(Integer id, ProblemDto problemDto) {
        final Optional<Problem> optionalProblem = problemRepository.findById(id);
        final boolean exists =
                problemRepository.existsByTitleAndIdNot(problemDto.getTitle(), id);

        if (exists) {
            return new Response("Already exists", false);
        }
        final Problem problem = optionalProblem.get();
        problem.setProblem(problemDto.getProblem());
        problem.setTitle(problemDto.getTitle());
        problem.setSolution(problemDto.getSolution());
        problem.setBody(problemDto.getBody());
        final Optional<Section> optionalSection = sectionRepository.findById(problemDto.getSectionId());
        if (optionalSection.isEmpty()) {
            return new Response("Section not found", false);
        }
        problem.setSection(optionalSection.get());

        problemRepository.save(problem);
        return new Response("Added", true);
    }

}