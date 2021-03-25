package uz.pdp.demo.task1.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task1.entity.Company;
import uz.pdp.demo.task1.entity.Department;
import uz.pdp.demo.task1.entity.Response;
import uz.pdp.demo.task1.payload.CompanyDto;
import uz.pdp.demo.task1.payload.DepartmentDto;
import uz.pdp.demo.task1.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = service.delete(id);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody DepartmentDto address) {
        final Response response = service.edit(id, address);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody DepartmentDto address) {
        final Response response = service.add(address);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<Department> addresses = service.get();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final Department address = service.getById(id);
        return ResponseEntity.ok(address);
    }
}
