package uz.pdp.demo.task1.controller;

import lombok.experimental.PackagePrivate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task1.entity.Address;
import uz.pdp.demo.task1.entity.Company;
import uz.pdp.demo.task1.entity.Response;
import uz.pdp.demo.task1.payload.CompanyDto;
import uz.pdp.demo.task1.repository.CompanyRepository;
import uz.pdp.demo.task1.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = companyService.delete(id);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody CompanyDto address) {
        final Response response = companyService.edit(id, address);
        return ResponseEntity
                .status(response.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT)
                .body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CompanyDto address) {
        final Response response = companyService.add(address);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<Company> addresses = companyService.get();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final Company address = companyService.getById(id);
        return ResponseEntity.ok(address);
    }
}
