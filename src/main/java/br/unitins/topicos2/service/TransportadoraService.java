package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.TransportadoraDTO;
import br.unitins.topicos2.dto.TransportadoraResponseDTO;
import jakarta.validation.Valid;

public interface TransportadoraService {

    // recursos basicos
    List<TransportadoraResponseDTO> getAll(int page, int pageSize);

    TransportadoraResponseDTO findById(Long id);

    TransportadoraResponseDTO create(@Valid TransportadoraDTO dto);

    TransportadoraResponseDTO update(Long id, TransportadoraDTO dto);

    void delete(Long id);

    // recursos extras

    List<TransportadoraResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}