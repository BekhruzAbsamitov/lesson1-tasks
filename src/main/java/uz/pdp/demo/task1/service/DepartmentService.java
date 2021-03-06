package uz.pdp.demo.task1.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task1.entity.Company;
import uz.pdp.demo.task1.entity.Department;
import uz.pdp.demo.task1.entity.Response;
import uz.pdp.demo.task1.payload.DepartmentDto;
import uz.pdp.demo.task1.repository.CompanyRepository;
import uz.pdp.demo.task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;
    CompanyRepository companyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public Response delete(Integer id) {
        final Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new Response("Department not found", false);
        }
        departmentRepository.deleteById(id);
        return new Response("Deleted!", true);
    }

    public Response edit(Integer id, DepartmentDto departmentDto) {
        final Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) {
            return new Response("Not found", false);
        }
        final Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new Response("Address not found", false);
        }
        final boolean exists = companyRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (exists) {
            return new Response("Company already exists", false);
        }

        final Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());

        departmentRepository.save(department);
        return new Response("Edited!", true);

    }

    public Response add(DepartmentDto companyDto) {
        final boolean exists = companyRepository.existsByName(companyDto.getName());
        if (exists) {
            return new Response("Depart already exists", false);
        }
        Department department = new Department();
        department.setName(companyDto.getName());
        final Optional<Company> optionalCompany = companyRepository.findById(companyDto.getCompanyId());
        if (optionalCompany.isEmpty()) {
            return new Response("Address not found", false);
        }
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new Response("Added!", true);
    }

    public List<Department> get() {
        return departmentRepository.findAll();
    }

    public Department getById(Integer id) {
        final Optional<Department> optionalCompany =
                departmentRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return null;
        }
        return optionalCompany.get();
    }
}