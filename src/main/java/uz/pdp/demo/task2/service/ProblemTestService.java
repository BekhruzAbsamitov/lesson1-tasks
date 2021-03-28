package uz.pdp.demo.task2.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task2.entiry.Problem;
import uz.pdp.demo.task2.entiry.ProblemTest;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.payload.ProblemTestDto;
import uz.pdp.demo.task2.repository.ProblemRepository;
import uz.pdp.demo.task2.repository.ProblemTestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTestService {

    final ProblemTestRepository problemTestRepository;
    final ProblemRepository problemRepository;

    public ProblemTestService(ProblemTestRepository problemTestRepository, ProblemRepository problemRepository) {
        this.problemTestRepository = problemTestRepository;
        this.problemRepository = problemRepository;
    }

    public ProblemTest getById(Integer id) {
        final Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if (optionalProblemTest.isEmpty())
            return null;
        return optionalProblemTest.get();
    }

    public List<ProblemTest> get() {
        return problemTestRepository.findAll();
    }

    public Response add(ProblemTestDto problemTestDto) {
        ProblemTest problemTest = new ProblemTest();
        final Optional<Problem> optionalProblem = problemRepository.findById(problemTestDto.getProblemId());
        if (optionalProblem.isEmpty()) {
            return new Response("Not found", false);
        }
        problemTest.setProblem(optionalProblem.get());
        problemTest.setArgument(problemTestDto.getArgument());
        problemTest.setResult(problemTestDto.getResult());
        problemTestRepository.save(problemTest);
        return new Response("Added", true);
    }

    public Response edit(Integer id, ProblemTestDto problemTestDto) {
        final Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if (optionalProblemTest.isEmpty()) {
            return new Response("Problem test not found", false);
        }
        final ProblemTest problemTest = optionalProblemTest.get();
        final Optional<Problem> optionalProblem = problemRepository.findById(problemTestDto.getProblemId());
        if (optionalProblem.isEmpty()) {
            return new Response("Not found", false);
        }
        problemTest.setProblem(optionalProblem.get());
        problemTest.setArgument(problemTestDto.getArgument());
        problemTest.setResult(problemTestDto.getResult());
        problemTestRepository.save(problemTest);
        return new Response("Edited", true);
    }

    public Response delete(Integer id) {
        final Optional<ProblemTest> optionalProblemTest = problemTestRepository.findById(id);
        if (optionalProblemTest.isEmpty()) {
            return new Response("Not found", false);
        }
        problemTestRepository.deleteById(id);
        return new Response("Deleted", false);
    }
}
