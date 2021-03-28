package uz.pdp.demo.task2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task2.entiry.ProblemTest;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.payload.ProblemTestDto;
import uz.pdp.demo.task2.repository.ProblemTestRepository;
import uz.pdp.demo.task2.service.ProblemTestService;

import java.util.List;

@RestController
public class ProblemTestController {

    ProblemTestService problemTestService;

    public ProblemTestController(ProblemTestService problemTestService) {
        this.problemTestService = problemTestService;
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final ProblemTest problemTest = problemTestService.getById(id);
        return ResponseEntity.status(problemTest != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(problemTest);
    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<ProblemTest> problemTests = problemTestService.get();
        return ResponseEntity.status(HttpStatus.OK).body(problemTests);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = problemTestService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody ProblemTestDto problemTestDto) {
        final Response response = problemTestService.add(problemTestDto);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> put(@PathVariable Integer id, @RequestBody ProblemTestDto problemTest) {
        final Response response = problemTestService.edit(id, problemTest);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }
}
