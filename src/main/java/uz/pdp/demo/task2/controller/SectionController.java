package uz.pdp.demo.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.Section;
import uz.pdp.demo.task2.payload.SectionDto;
import uz.pdp.demo.task2.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    SectionService service;

    public SectionController(SectionService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = service.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody SectionDto sectionDto) {
        final Response response = service.edit(id, sectionDto);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(SectionDto sectionDto) {
        final Response response = service.add(sectionDto);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final Section section = service.getById(id);
        return ResponseEntity.status(section != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(section);
    }


    @GetMapping
    public HttpEntity<?> get() {
        final List<Section> sections = service.get();
        return ResponseEntity.status(HttpStatus.OK).body(sections);
    }
}
