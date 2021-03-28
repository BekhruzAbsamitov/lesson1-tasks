package uz.pdp.demo.task2.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.UserPractice;
import uz.pdp.demo.task2.payload.UserPracticeDto;
import uz.pdp.demo.task2.service.UserPracticeService;

import java.util.List;

public class UserPracticeController {

    UserPracticeService userPracticeService;

    public UserPracticeController(UserPracticeService userPracticeService) {
        this.userPracticeService = userPracticeService;
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        final UserPractice userPractice =
                userPracticeService.getById(id);
        return ResponseEntity.status(userPractice != null ? HttpStatus.OK : HttpStatus.CONFLICT).body(userPractice);
    }

    @GetMapping
    public HttpEntity<?> get() {
        final List<UserPractice> userPractices = userPracticeService.get();
        return ResponseEntity.status(HttpStatus.OK).body(userPractices);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserPracticeDto userPracticeDto) {
        final Response response = userPracticeService.edit(id, userPracticeDto);
        return ResponseEntity.status(response.isStatus()? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody UserPracticeDto userPracticeDto) {
        final Response response = userPracticeService.add(userPracticeDto);
        return ResponseEntity.status(response.isStatus()? HttpStatus.OK : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        final Response response = userPracticeService.delete(id);
        return ResponseEntity.status(response.isStatus()? HttpStatus.NOT_FOUND : HttpStatus.CONFLICT).body(response);
    }
}
