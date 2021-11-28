package uz.pdp.task1.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.payload.AddressDto;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.repositary.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    public ApiResponse addAddress(AddressDto addressDto) {
        addressRepository.save(new Address(addressDto.getStreet(), addressDto.getHomeNumber()));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editAddress(int id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("address not found", false);
        Address address = optionalAddress.get();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteAddress(int id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return new ApiResponse("success full deleted", true);
        }
        return new ApiResponse("address not found", false);
    }
}
