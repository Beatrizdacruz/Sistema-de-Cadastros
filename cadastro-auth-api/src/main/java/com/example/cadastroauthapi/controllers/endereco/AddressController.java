package com.example.cadastroauthapi.controllers.endereco;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.domain.Address;
import com.example.cadastroauthapi.domain.User;
import com.example.cadastroauthapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @GetMapping("/list-endereco/{user_id}")
    public ResponseEntity<List<Address>> getEnderecos(@PathVariable String user_id, User user) {
        try {

            //String user_id = user.getId();
            List<Address> address = addressService.listAddress(user_id);
            return ResponseEntity.ok(address);

        }catch (Exception exception){

            ResponseEntity.badRequest().body("Ocorreu um erro ao listar endereço");

        }
        return null;
    }

   @PostMapping("/add-endereco")
    public ResponseEntity<?> criarEndereco(@RequestBody Address address) {
        try {
            Address novoEndereco = addressService.createAddress(address);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Endereço criado com sucesso. ID: " + novoEndereco.getId());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao adicionar endereço: " + exception.getMessage());
        }
    }

    @PutMapping("/edit-endereco/{addressId}")
    public ResponseEntity<String> editarEndereco(@PathVariable String addressId, @RequestBody Address address){
        try {
            addressService.updateAddress(addressId, address);
            return ResponseEntity.ok("Endereço editado com sucesso");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Ocorreu um erro ao editar endereço");
        }
    }

    @DeleteMapping("/delete-endereco/{addressId}")
    public ResponseEntity<String> deletarEndereco(@PathVariable String addressId){
        try {
            addressService.deleteAddress(addressId);
            return ResponseEntity.ok("Endereço deletado com sucesso");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body("Ocorreu um erro ao deletar endereço");
        }

    }

}
