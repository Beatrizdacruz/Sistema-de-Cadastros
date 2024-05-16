package com.example.cadastroauthapi.Repositories;

import com.example.cadastroauthapi.domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    // Método para buscar todas as associações de um usuário pelo seu ID
    @Query("SELECT ua FROM UserAddress ua JOIN ua.user u WHERE u.id = :userId")
    List<UserAddress> findByUserId(String userId);

    UserAddress findByUserIdAndAddressId(String userId, String addressId);


    // Método para buscar todas as associações de um endereço pelo seu ID
    List<UserAddress> findByAddressId(String addressId);

    // Método para excluir associações de um usuário pelo seu ID
    void deleteByUserId(String userId);

    // Método para excluir associações de um endereço pelo seu ID
    void deleteByAddressId(String addressId);
}
