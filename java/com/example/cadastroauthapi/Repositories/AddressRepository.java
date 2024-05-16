package com.example.cadastroauthapi.Repositories;

import com.example.cadastroauthapi.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {
    @Query("SELECT a FROM Address a WHERE a.user_id = ?1")
    public List<Address> findAddressesByUserId(String user_id);

    Address findByCepAndLogradouroAndBairroAndLocalidadeAndUf(
            String cep, String logradouro, String bairro, String localidade, String uf);

}
