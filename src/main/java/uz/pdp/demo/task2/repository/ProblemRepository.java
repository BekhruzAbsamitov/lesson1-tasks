package uz.pdp.demo.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.demo.task2.entiry.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    boolean existsByTitleAndIdNot(String title, Integer id);

}
