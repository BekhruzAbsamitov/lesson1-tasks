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
        final Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return new Response("Company not found", false);
        }
        workerRepository.deleteById(id);
        return new Response("Deleted!", true);
    }

    public Response edit(Integer id, WorkerDto workerDto) {
        final Optional<Worker> optionalCompany = workerRepository.findById(id);
        if (optionalCompany.isEmpty()) {
            return new Response("Not found", false);
        }
        final Optional<Department> optionalAddress = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalAddress.isEmpty()) {
            return new Response("Department not found", false);
        }

        final Worker worker = optionalCompany.get();

        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        addressRepository.save(address);

        final Department department = optionalAddress.get();
        worker.setDepartment(department);
        worker.setAddress(address);
        workerRepository.save(worker);
        return new Response("Edited!", true);

    }

    public Response add(WorkerDto workerDto) {
        Worker worker = new Worker();
        worker.setName(workerDto.getName());

        final Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) {
            return new Response("Department not found", false);
        }

        final Department department = optionalDepartment.get();
        final Address a = new Address();

        a.setHomeNumber(workerDto.getHomeNumber());
        a.setStreet(workerDto.getStreet());
        addressRepository.save(a);

        worker.setDepartment(department);
        worker.setAddress(a);

        workerRepository.save(worker);
        return new Response("Edited!", true);
    }

    public List<Worker> get() {
        return workerRepository.findAll();
    }

    public Worker getById(Integer id) {
        final Optional<Worker> optionalWorker =
                workerRepository.findById(id);
        if (optionalWorker.isEmpty()) {
            return null;
        }
        return optionalWorker.get();
    }
}
