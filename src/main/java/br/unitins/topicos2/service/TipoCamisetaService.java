package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.TipoCamisetaDTO;
import br.unitins.topicos2.dto.TipoCamisetaResponseDTO;
import jakarta.validation.Valid;

public interface TipoCamisetaService {

    // recursos basicos
    List<TipoCamisetaResponseDTO> getAll(int page, int pageSize);

    TipoCamisetaResponseDTO findById(Long id);

    TipoCamisetaResponseDTO create(@Valid TipoCamisetaDTO dto);

    TipoCamisetaResponseDTO update(Long id, TipoCamisetaDTO dto);

    void delete(Long id);

    // recursos extras

    List<TipoCamisetaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}
