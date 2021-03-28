package uz.pdp.demo.task2.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task2.entiry.Problem;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.payload.ProblemDto;
import uz.pdp.demo.task2.service.ProblemService;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = problemService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProblemDto problemDto) {
        final Problem problem = problemService.getById(id);
        return ResponseEntity.status(problem != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(problem);
    }

    @PostMapping
    public HttpEntity<?> add(ProblemDto problemDto) {
        final Response response = problemService.add(problemDto);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);

    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<Problem> problems = problemService.get();
        return ResponseEntity.status(HttpStatus.OK).body(problems);
    }

    @GetMapping("/{id}")
    public HttpEntity<Problem> getById(@PathVariable Integer id) {
        final Problem problem = problemService.getById(id);
        return ResponseEntity.status(problem != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(problem);
    }

}
