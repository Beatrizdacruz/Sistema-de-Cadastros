package com.example.cadastroauthapi.service;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.Repositories.UserAddressRepository;
import com.example.cadastroauthapi.domain.Address;
import com.example.cadastroauthapi.domain.User;
import com.example.cadastroauthapi.domain.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    public List<Address> listAddress(String user_id){
        System.out.println("passandoo");

        return addressRepository.findAddressesByUserId(user_id);
    }

    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

//    public Address updateAddress(String addressId, Address updatedAddress){
//        Address existingAddress = addressRepository.findById(addressId).orElse(null);
//        if(existingAddress != null){
//            existingAddress.setCep(updatedAddress.getCep());
//            existingAddress.setLogradouro(updatedAddress.getLogradouro());
//            existingAddress.setBairro(updatedAddress.getBairro());
//            existingAddress.setLocalidade(updatedAddress.getLocalidade());
//            existingAddress.setUf(updatedAddress.getUf());
//            return addressRepository.save(existingAddress);
//        }
//        return null;
//    }

    public void deleteAddress(String addressId){
        addressRepository.deleteById(addressId);
    }

    //mudando a partir daqui:


    public List<Address> listAddressByUserId(String userId) {
        try{
//            System.out.println("OXEEEEEEEEEEEEEEEEEEEEEEEE");
            List<UserAddress> userAddresses = userAddressRepository.findByUserId(userId);
//            System.out.println(userAddresses);
//            System.out.println("passou aqui");
            return userAddresses.stream().map(UserAddress::getAddress).collect(Collectors.toList());
        }catch (Exception exception){
            System.out.println("Erro em list Address");
            System.out.println(exception);
            return null;
        }

    }

    public void addAddressForUser(User user, Address address) {
        Address existingAddress = addressRepository.findByCepAndLogradouroAndBairroAndLocalidadeAndUf(
                address.getCep(), address.getLogradouro(), address.getBairro(), address.getLocalidade(), address.getUf());

        if (existingAddress == null) {
            existingAddress = addressRepository.save(address);
        }
        UserAddress userAddress = new UserAddress(user, existingAddress);
        userAddressRepository.save(userAddress);
    }

    public void deleteAddressForUser(String userId, String addressId) {
        UserAddress userAddress = userAddressRepository.findByUserIdAndAddressId(userId, addressId);
        if (userAddress != null) {
            userAddressRepository.delete(userAddress);
        }
    }

    public Address updateAddress(String addressId, Address updatedAddress) {
        // Verificar se já existe um endereço com as mesmas informações no banco de dados
        Address existingAddress = addressRepository.findByCepAndLogradouroAndBairroAndLocalidadeAndUf(
                updatedAddress.getCep(), updatedAddress.getLogradouro(), updatedAddress.getBairro(),
                updatedAddress.getLocalidade(), updatedAddress.getUf());

        if (existingAddress != null) {
            // Se já existir um endereço com as mesmas informações, atualize o ID do endereço atual
            updatedAddress.setId(existingAddress.getId());
        } else {
            // Se não existir, salve o novo endereço no banco de dados
            updatedAddress = addressRepository.save(updatedAddress);
        }

        // Atualizar as entradas na tabela user_address com o novo ID do endereço
        List<UserAddress> userAddresses = userAddressRepository.findByAddressId(addressId);
        for (UserAddress userAddress : userAddresses) {
            userAddress.setAddress(updatedAddress);
        }
        userAddressRepository.saveAll(userAddresses);

        return updatedAddress;
    }

}
