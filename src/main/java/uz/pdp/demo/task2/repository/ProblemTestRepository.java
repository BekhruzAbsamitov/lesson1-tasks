package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.ProblemTest;

public interface ProblemTestRepository extends JpaRepository<ProblemTest, Integer> {
}
