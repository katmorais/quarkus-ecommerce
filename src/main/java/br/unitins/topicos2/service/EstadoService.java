package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EstadoDTO;
import br.unitins.topicos2.dto.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {

    // recursos basicos
    List<EstadoResponseDTO> getAll(int page, int pageSize);

    EstadoResponseDTO findById(Long id);

    EstadoResponseDTO create(@Valid EstadoDTO dto);

    EstadoResponseDTO update(Long id, EstadoDTO dto);

    void delete(Long id);

    // recursos extras

    List<EstadoResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}