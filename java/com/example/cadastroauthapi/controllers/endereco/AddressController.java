package com.example.cadastroauthapi.controllers.endereco;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.Repositories.UserAddressRepository;
import com.example.cadastroauthapi.domain.Address;
import com.example.cadastroauthapi.domain.User;
import com.example.cadastroauthapi.domain.UserAddress;
import com.example.cadastroauthapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
@Configuration
@EnableWebSecurity

@RestController
@RequestMapping("/enderecos")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    UserAddressRepository userAddressRepository;

//    @GetMapping("/list-endereco/")
//    public ResponseEntity<List<Address>> getEnderecos(@AuthenticationPrincipal User user) {
//        try {
//
//            String user_id = user.getId();
//            System.out.println(user_id);
//            List<Address> address = addressService.listAddress(user_id);
//            return ResponseEntity.ok(address);
//
//        }catch (Exception exception){
//
//            ResponseEntity.badRequest().build();
//
//        }
//        return null;
//    }


    @GetMapping("/list-endereco/")
    public ResponseEntity<List<Address>> getEnderecos(@AuthenticationPrincipal User user) {
        try {
            String user_id = user.getId();
            System.out.println(user_id);
            List<Address> addresses = addressService.listAddressByUserId(user_id);
            System.out.println(addresses);
            return ResponseEntity.ok(addresses);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }


//    @PostMapping("/add-endereco")
//    public ResponseEntity<?> criarEndereco(@AuthenticationPrincipal User user, @RequestBody Address address) {
//        try {
//            address.setUser_id(user.getId());
//            Address novoEndereco = addressService.createAddress(address);
//            System.out.println("aqui eu passo o novo endereço>>>>>>");
//
//            System.out.println(novoEndereco);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("");
//        } catch (Exception exception) {
//            System.out.println("porqueeeeeeeeeeeeeee");
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }

    @PostMapping("/add-endereco")
    public ResponseEntity<?> criarEndereco(@AuthenticationPrincipal User user, @RequestBody Address address) {
        try {
            addressService.addAddressForUser(user, address);
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//    @PutMapping("/edit-endereco/{addressId}")
//    public ResponseEntity<String> editarEndereco(@PathVariable String addressId, @RequestBody Address address){
//        try {
//            addressService.updateAddress(addressId, address);
//            return ResponseEntity.ok("");
//        }catch (Exception exception){
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PutMapping("/edit-endereco/{addressId}")
    public ResponseEntity<String> editarEndereco(@PathVariable String addressId, @RequestBody Address address) {
        try {
            addressService.updateAddress2(addressId, address);
            return ResponseEntity.ok("");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @DeleteMapping("/delete-endereco/{addressId}")
//    public ResponseEntity<String> deletarEndereco(@PathVariable String addressId){
//        try {
//            addressService.deleteAddress(addressId);
//            return ResponseEntity.ok("");
//        }catch (Exception exception){
//            return ResponseEntity.badRequest().build();
//        }
//
//    }


    @DeleteMapping("/delete-endereco/{addressId}")
    public ResponseEntity<String> deletarEndereco(@PathVariable String addressId, @AuthenticationPrincipal User user) {
        try {
            String userId = user.getId();
            addressService.deleteAddressForUser(userId, addressId);
            return ResponseEntity.ok("");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}
