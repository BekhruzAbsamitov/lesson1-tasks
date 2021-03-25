package uz.pdp.demo.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.demo.task1.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByHomeNumberAndStreet(Integer homeNumber, String street);

    boolean existsByHomeNumberAndStreetAndIdNot(Integer homeNumber, String street, Integer id);

}
