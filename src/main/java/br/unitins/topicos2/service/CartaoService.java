package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CartaoDTO;
import br.unitins.topicos2.dto.CartaoResponseDTO;
import jakarta.validation.Valid;

public interface CartaoService {

    // recursos basicos
    List<CartaoResponseDTO> getAll(int page, int pageSize);

    CartaoResponseDTO findById(Long id);

    CartaoResponseDTO create(@Valid CartaoDTO dto);

    CartaoResponseDTO update(Long id, CartaoDTO dto);

    void delete(Long id);

    // recursos extras

    List<CartaoResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}