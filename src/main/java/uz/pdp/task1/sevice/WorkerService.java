package uz.pdp.task1.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Department;
import uz.pdp.task1.entity.Worker;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.WorkerDto;
import uz.pdp.task1.repositary.AddressRepository;
import uz.pdp.task1.repositary.DepartmentRepository;
import uz.pdp.task1.repositary.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    WorkerRepository workerRepository;

    public List<Worker> getWorker() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(int id) {
        return workerRepository.findById(id).orElse(null);
    }

    public ApiResponse addWorker(WorkerDto workerDto) {
        if (workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new ApiResponse("this phoneNumber is alredy exists", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);
        Address address = addressRepository.save(new Address(workerDto.getStreet(), workerDto.getHomeNumber()));
        workerRepository.save(new Worker(workerDto.getName(), workerDto.getPhoneNumber(), address, optionalDepartment.get()));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editWorker(int id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("worker not found", false);
        if (workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id))
            return new ApiResponse("this phoneNumber is alredy exists", false);
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);
        Address address = optionalWorker.get().getAddress();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        address = addressRepository.save(address);
        Worker worker = optionalWorker.get();
        worker.setAddress(address);
        worker.setDepartment(optionalDepartment.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteWorker(int id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("worker not found", false);
        addressRepository.deleteById(optionalWorker.get().getAddress().getId());
        return new ApiResponse("success full deleted", true);
    }
}
