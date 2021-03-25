package uz.pdp.demo.task1.service;

import org.springframework.stereotype.Service;
import uz.pdp.demo.task1.entity.*;
import uz.pdp.demo.task1.payload.DepartmentDto;
import uz.pdp.demo.task1.payload.WorkerDto;
import uz.pdp.demo.task1.repository.AddressRepository;
import uz.pdp.demo.task1.repository.DepartmentRepository;
import uz.pdp.demo.task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    WorkerRepository workerRepository;
    DepartmentRepository departmentRepository;
    AddressRepository addressRepository;

    public WorkerService(WorkerRepository workerRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    public Response delete(Integer id) {
        final Optional<Worker> optionalCompany = workerRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new Response("Company not found", false);
        }
        workerRepository.deleteById(id);
        return new Response("Deleted!", true);
    }

    public Response edit(Integer id, WorkerDto companyDto) {
        final Optional<Worker> optionalCompany = workerRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new Response("Not found", false);
        }
        final Optional<Department> optionalAddress = departmentRepository.findById(companyDto.getDepartmentId());
        if (optionalAddress.isEmpty()) {
            return new Response("Department not found", false);
        }

        final Worker worker = optionalCompany.get();
        final Optional<Address> address = addressRepository.findById(companyDto.getAddressId());
        if (address.isEmpty()) {
            return new Response("Address not found", false);
        }


        final Department department = optionalAddress.get();
        final Address address1 = address.get();
        worker.setDepartment(department);
        worker.setAddress(address1);

        workerRepository.save(worker);
        return new Response("Edited!", true);

    }

    public Response add(WorkerDto companyDto) {
        Worker worker = new Worker();
        worker.setName(companyDto.getName());

        final Optional<Address> address = addressRepository.findById(companyDto.getAddressId());
        if (address.isEmpty()) {
            return new Response("Address not found", false);
        }


        final Optional<Department> optionalAddress = departmentRepository.findById(companyDto.getDepartmentId());
        if (optionalAddress.isEmpty()) {
            return new Response("Department not found", false);
        }


        final Department department = optionalAddress.get();
        final Address address1 = address.get();
        worker.setDepartment(department);
        worker.setAddress(address1);

        workerRepository.save(worker);
        return new Response("Edited!", true);
    }

    public List<Worker> get() {
        return workerRepository.findAll();
    }

    public Worker getById(Integer id) {
        final Optional<Worker> optionalCompany =
                workerRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return null;
        }
        return optionalCompany.get();
    }
}
