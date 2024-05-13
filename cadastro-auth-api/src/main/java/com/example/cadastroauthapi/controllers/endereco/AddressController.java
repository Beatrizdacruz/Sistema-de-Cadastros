package com.example.cadastroauthapi.controllers.endereco;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.domain.Address;
import com.example.cadastroauthapi.domain.User;
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

    @GetMapping("/list-endereco/")
    public ResponseEntity<List<Address>> getEnderecos(@AuthenticationPrincipal User user) {
        try {

            String user_id = user.getId();
            System.out.println(user_id);
            List<Address> address = addressService.listAddress(user_id);
            return ResponseEntity.ok(address);

        }catch (Exception exception){

            ResponseEntity.badRequest().build();

        }
        return null;
    }

    @PostMapping("/add-endereco")
    public ResponseEntity<?> criarEndereco(@AuthenticationPrincipal User user, @RequestBody Address address) {
        try {
            address.setUser_id(user.getId());
            Address novoEndereco = addressService.createAddress(address);
            System.out.println("aqui eu passo o novo endereço>>>>>>");

            System.out.println(novoEndereco);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Endereço criado com sucesso. ID: " + novoEndereco.getId());
        } catch (Exception exception) {
            System.out.println("porqueeeeeeeeeeeeeee");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


    @PutMapping("/edit-endereco/{addressId}")
    public ResponseEntity<String> editarEndereco(@PathVariable String addressId, @RequestBody Address address){
        try {
            addressService.updateAddress(addressId, address);
            return ResponseEntity.ok("");
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete-endereco/{addressId}")
    public ResponseEntity<String> deletarEndereco(@PathVariable String addressId){
        try {
            addressService.deleteAddress(addressId);
            return ResponseEntity.ok("");
        }catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }

    }

}
