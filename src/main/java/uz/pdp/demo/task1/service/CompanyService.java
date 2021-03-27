package uz.pdp.demo.task1.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task1.entity.Address;
import uz.pdp.demo.task1.entity.Company;
import uz.pdp.demo.task1.entity.Response;
import uz.pdp.demo.task1.payload.CompanyDto;
import uz.pdp.demo.task1.repository.AddressRepository;
import uz.pdp.demo.task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    CompanyRepository companyRepository;
    AddressRepository addressRepository;

    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    public Response delete(Integer id) {
        final Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isEmpty()) {
            return new Response("Company not found", false);
        }

        companyRepository.deleteById(id);
        return new Response("Deleted!", true);
    }

    public Response edit(Integer id, CompanyDto companyDto) {
        final Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isEmpty()) {
            return new Response("Not found", false);
        }

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);

        final boolean exists = companyRepository.existsByNameAndIdNot(companyDto.getName(), id);
        if (exists) {
            return new Response("Company already exists", false);
        }

        final Company company = optionalCompany.get();
        company.setName(companyDto.getName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        companyRepository.save(company);
        return new Response("Edited!", true);

    }

    public Response add(CompanyDto companyDto) {

        final boolean exists = companyRepository.existsByName(companyDto.getName());
        if (exists) {
            return new Response("Company already exists", false);
        }

        Company company = new Company();

        company.setDirectorName(companyDto.getDirectorName());
        company.setName(companyDto.getName());

        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        addressRepository.save(address);
        company.setAddress(address);

        companyRepository.save(company);
        return new Response("Added successfully!", true);
    }

    public List<Company> get() {
        return companyRepository.findAll();
    }

    public Company getById(Integer id) {
        final Optional<Company> optionalCompany =
                companyRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return null;
        }
        return optionalCompany.get();
    }
}
