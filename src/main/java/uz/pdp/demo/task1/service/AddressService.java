package uz.pdp.demo.task1.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task1.entity.Address;
import uz.pdp.demo.task1.entity.Response;
import uz.pdp.demo.task1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Response delete(Integer id) {
        final Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepository.deleteById(id);
            return new Response("Deleted!", true);
        }
        return new Response("Not found", false);
    }

    public Response edit(Integer id, Address address) {
        final Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return new Response("Address not found", false);
        }

        final boolean exists =
                addressRepository.existsByHomeNumberAndStreetAndIdNot(address.getHomeNumber(), address.getStreet(), id);
        if (exists) {
            return new Response("Address already exists", false);
        }

        final Address a = optionalAddress.get();
        a.setHomeNumber(address.getHomeNumber());
        a.setStreet(address.getStreet());
        addressRepository.save(a);
        return new Response("Edited!", true);
    }

    public Response add(Address address) {
        final boolean isExists = addressRepository.existsByHomeNumberAndStreet(address.getHomeNumber(), address.getStreet());
        if (isExists) {
            return new Response("Address already exists", false);
        }
        addressRepository.save(address);
        return new Response("Added!", true);
    }

    public List<Address> get() {
        return addressRepository.findAll();
    }

    public Address getById(Integer id) {
        final Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return null;
        }
        return optionalAddress.get();
    }

}
