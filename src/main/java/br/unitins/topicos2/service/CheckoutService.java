package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.CheckoutDTO;
import jakarta.validation.Valid;

public interface CheckoutService {

//    CheckoutResponseDTO findById(Long id);

    void create(@Valid CheckoutDTO dto);
}