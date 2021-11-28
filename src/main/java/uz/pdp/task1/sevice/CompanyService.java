package uz.pdp.task1.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.CompanyDto;
import uz.pdp.task1.repositary.AddressRepository;
import uz.pdp.task1.repositary.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto) {
        Address savedAddress = addressRepository.save(new Address(companyDto.getStreet(), companyDto.getHomeNumber()));
        companyRepository.save(new Company(companyDto.getCorpName(), companyDto.getDirectorName(), savedAddress));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editCompany(int id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);
        Company company = optionalCompany.get();
        Address address = company.getAddress();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        address = addressRepository.save(address);
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(address);
        companyRepository.save(company);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteCompany(int id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("company not found", false);
        addressRepository.deleteById(optionalCompany.get().getAddress().getId());
        companyRepository.deleteById(id);
        return new ApiResponse("succes full deleted", true);
    }
}
