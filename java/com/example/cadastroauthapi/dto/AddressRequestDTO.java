package com.example.cadastroauthapi.dto;

import com.example.cadastroauthapi.domain.Address;

public class AddressRequestDTO {

    private String cep;

    private String bairro;

    private String localidade;

    private String logradouro;

    private String uf;

    // Getters e Setters

    public Address toAddress() {
        Address address = new Address();
        address.setCep(this.cep);
        address.setBairro(this.bairro);
        address.setLocalidade(this.localidade);
        address.setLogradouro(this.logradouro);
        address.setUf(this.uf);

        // Você precisa de City e State para criar o endereço,
        // mas estou assumindo que você terá essas informações disponíveis na sua aplicação.
        return address;
    }
}
