package com.example.cadastroauthapi.controllers.endereco;

import com.example.cadastroauthapi.Repositories.AddressRepository;
import com.example.cadastroauthapi.domain.endereco.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@Configuration
@EnableWebSecurity

@RestController
@RequestMapping("/enderecos")
public class AddressController {
    @Autowired
    private AddressRepository enderecoRepository;

    @GetMapping("/list-endereco")
    public List<Address> getEnderecos() {
        try {
            return enderecoRepository.findAll();
        }catch (Exception exception){
            ResponseEntity.badRequest().build();
        }
        return null;
    }

    @PostMapping("/add-endereco")
    public ResponseEntity<String> criarEndereco(@RequestBody Address address) {
        try {
            enderecoRepository.save(address);
            return ResponseEntity.ok("Endereço criado com sucesso");
        }catch (Exception exception){
            ResponseEntity.badRequest().build();
        }
        //ResponseEntity.badRequest().build();
        return null;
    }
}
