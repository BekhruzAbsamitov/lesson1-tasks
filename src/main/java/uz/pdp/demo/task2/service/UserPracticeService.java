package uz.pdp.demo.task2.service;

import uz.pdp.demo.task2.entiry.Problem;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.User;
import uz.pdp.demo.task2.entiry.UserPractice;
import uz.pdp.demo.task2.payload.UserPracticeDto;
import uz.pdp.demo.task2.repository.ProblemRepository;
import uz.pdp.demo.task2.repository.UserPracticeRepository;
import uz.pdp.demo.task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserPracticeService {
    UserPracticeRepository userPracticeRepository;
    UserRepository userRepository;
    ProblemRepository problemRepository;

    public UserPracticeService(UserPracticeRepository userPracticeRepository, UserRepository userRepository, ProblemRepository problemRepository) {
        this.userPracticeRepository = userPracticeRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    public Response delete(Integer id) {
        final Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        if (optionalUserPractice.isEmpty()) {
            return new Response("Not found", false);
        }
        userPracticeRepository.deleteById(id);
        return new Response("Deleted", true);
    }

    public Response add(UserPracticeDto userPracticeDto) {
        UserPractice userPractice = new UserPractice();

        final Optional<User> optionalUser = userRepository.findById(userPracticeDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new Response("User not found", false);
        }
        userPractice.setUser(optionalUser.get());

        final Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDto.getProblemId());
        if (optionalProblem.isEmpty()) {
            return new Response("Problem not found", false);
        }
        userPractice.setProblem(optionalProblem.get());

        userPractice.setUserSolution(userPracticeDto.getUserSolution());
        userPractice.setDate(userPracticeDto.getDate());
        userPractice.setScore(userPracticeDto.getScore());
        userPracticeRepository.save(userPractice);
        return new Response("Added!", true);
    }

    public Response edit(Integer id, UserPracticeDto userPracticeDto) {
        final Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        if (optionalUserPractice.isEmpty()) {
            return new Response("User Practice not found", false);
        }
        final UserPractice userPractice = optionalUserPractice.get();

        final Optional<User> optionalUser = userRepository.findById(userPracticeDto.getUserId());
        if (optionalUser.isEmpty()) {
            return new Response("User not found", false);
        }
        userPractice.setUser(optionalUser.get());

        final Optional<Problem> optionalProblem = problemRepository.findById(userPracticeDto.getProblemId());
        if (optionalProblem.isEmpty()) {
            return new Response("Problem not found", false);
        }
        userPractice.setProblem(optionalProblem.get());

        userPractice.setUserSolution(userPracticeDto.getUserSolution());
        userPractice.setDate(userPracticeDto.getDate());
        userPractice.setScore(userPracticeDto.getScore());
        userPracticeRepository.save(userPractice);
        return new Response("Edited!", true);
    }

    public List<UserPractice> get() {
        return userPracticeRepository.findAll();
    }

    public UserPractice getById(Integer id) {
        final Optional<UserPractice> optionalUserPractice = userPracticeRepository.findById(id);
        if (optionalUserPractice.isEmpty()) {
            return null;
        }
        return optionalUserPractice.get();
    }
}
