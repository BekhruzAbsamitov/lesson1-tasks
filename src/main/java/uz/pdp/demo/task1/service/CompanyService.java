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

        company.setAddress(address);
        companyRepository.save(company);

        return new Response("Added!", true);
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
/**
 * public class CompanyServiceImpl implements CompanyService {
 * <p>
 * final CompanyRepository companyRepository;
 * final AddressRepository addressRepository;
 * <p>
 * public CompanyServiceImpl(CompanyRepository companyRepository,
 * AddressRepository addressRepository) {
 * this.companyRepository = companyRepository;
 * this.addressRepository = addressRepository;
 * }
 *
 * @Override public ApiResponse save(CompanyDTO companyDTO) {
 * if(companyRepository.existsByCorpName(companyDTO.getCorpName()))
 * return new ApiResponse("This company name is already exists!", false);
 * <p>
 * Company company = new Company();
 * company.setCorpName(companyDTO.getCorpName());
 * company.setDirectorName(companyDTO.getDirectorName());
 * <p>
 * Optional<Address> optionalAddress = addressRepository.findById(companyDTO.getAddressId());
 * if(!optionalAddress.isPresent())
 * return new ApiResponse("This address id is not found!", false);
 * company.setAddress(optionalAddress.get());
 * <p>
 * companyRepository.save(company);
 * return new ApiResponse("Address saved!", true);
 * }
 * @Override public List<Company> findAll() {
 * return companyRepository.findAll();
 * }
 * @Override public Company finOneById(Integer companyId) {
 * Optional<Company> optionalCompany = companyRepository.findById(companyId);
 * return optionalCompany.orElse(new Company());
 * }
 * @Override public ApiResponse edit(CompanyDTO companyDTO, Integer companyId) {
 * Optional<Company> optionalCompany = companyRepository.findById(companyId);
 * if(optionalCompany.isPresent()){
 * optionalCompany.get().setCorpName(companyDTO.getCorpName());
 * optionalCompany.get().setDirectorName(companyDTO.getDirectorName());
 * Optional<Address> optionalAddress = addressRepository.findById(companyDTO.getAddressId());
 * if(!optionalAddress.isPresent())
 * return new ApiResponse("This address id is not found!", false);
 * <p>
 * companyRepository.save(optionalCompany.get());
 * return new ApiResponse("Company updated!", true);
 * <p>
 * }
 * return new ApiResponse("Company not found!", false);
 * }
 * @Override public ApiResponse delete(Integer companyId) {
 * Optional<Company> optionalCompany = companyRepository.findById(companyId);
 * if(optionalCompany.isPresent()){
 * companyRepository.deleteById(companyId);
 * <p>
 * return new ApiResponse("Company deleted!", true);
 * }
 * return new ApiResponse("Company not found!", false);
 * }
 * }
 */
