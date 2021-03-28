package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.User;
import uz.pdp.demo.task2.entiry.UserPractice;

public interface UserPracticeRepository extends JpaRepository<UserPractice, Integer> {
}
