package com.example.cadastroauthapi.Repositories;

import com.example.cadastroauthapi.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {
    public List<Address> findAddressById(String user_id);
}
