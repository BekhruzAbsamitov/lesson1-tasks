package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndIdNot(String username, Integer id);

    boolean existsByEmailAndIdNot(String email, Integer id);
}
