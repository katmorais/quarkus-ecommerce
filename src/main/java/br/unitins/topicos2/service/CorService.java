package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CorDTO;
import br.unitins.topicos2.dto.CorResponseDTO;
import jakarta.validation.Valid;

public interface CorService {
    // recursos basicos
    List<CorResponseDTO> getAll(int page, int pageSize);

    CorResponseDTO findById(Long id);

    CorResponseDTO create(@Valid CorDTO dto);

    CorResponseDTO update(Long id, CorDTO dto);

    void delete(Long id);

    // recursos extras

    List<CorResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

    Object getStatus(Long id);

}