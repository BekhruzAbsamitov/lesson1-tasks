package uz.pdp.demo.task2.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task2.entiry.Response;
import uz.pdp.demo.task2.entiry.User;
import uz.pdp.demo.task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response delete(Integer id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new Response("User not found", false);
        }
        userRepository.deleteById(id);
        return new Response("Deleted", true);
    }

    public Response edit(Integer id, User user) {
        final Optional<User> optionalUser = userRepository.findById(id);

        final boolean emailAndIdNot = userRepository.existsByEmailAndIdNot(user.getEmail(), id);
        final boolean usernameAndIdNot = userRepository.existsByUsernameAndIdNot(user.getUsername(), id);

        if (optionalUser.isEmpty()) {
            return new Response("User not found", false);
        }

        if ((emailAndIdNot | usernameAndIdNot)) {
            return new Response("User already exits", false);
        }

        final User u = optionalUser.get();
        userRepository.save(u);
        return new Response("Added", true);
    }

    public Response add(User user) {
        final boolean email = userRepository.existsByEmail(user.getEmail());
        final boolean username = userRepository.existsByUsername(user.getUsername());

        if (email | username) {
            return new Response("User already exists", false);

        }
        userRepository.save(user);
        return new Response("Added", true);

    }

    public List<User> get() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        return optionalUser.get();
    }
}
