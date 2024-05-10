package com.example.cadastroauthapi.service;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address> listAddress(String user_id){
        return addressRepository.findAddressById(user_id);
    }

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    public Address updateAddress(String addressId, Address updatedAddress){
        Address existingAddress = addressRepository.findById(addressId).orElse(null);
        if(existingAddress != null){
            existingAddress.setCep(updatedAddress.getCep());
            existingAddress.setLogradouro(updatedAddress.getLogradouro());
            existingAddress.setBairro(updatedAddress.getBairro());
            existingAddress.setLocalidade(updatedAddress.getLocalidade());
            existingAddress.setUf(updatedAddress.getUf());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    public void deleteAddress(String addressId){
        addressRepository.deleteById(addressId);
    }

}
