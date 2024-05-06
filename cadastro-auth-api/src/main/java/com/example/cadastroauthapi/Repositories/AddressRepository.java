package com.example.cadastroauthapi.Repositories;

import com.example.cadastroauthapi.domain.endereco.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
