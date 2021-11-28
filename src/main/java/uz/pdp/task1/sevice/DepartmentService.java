package uz.pdp.task1.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.entity.Department;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.DepartmentDto;
import uz.pdp.task1.repositary.CompanyRepository;
import uz.pdp.task1.repositary.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);
        departmentRepository.save(new Department(departmentDto.getName(), optionalCompany.get()));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editDepartment(int id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteDepartment(int id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("department not found", false);
        departmentRepository.deleteById(id);
        return new ApiResponse("succes full deleted", true);
    }
}
