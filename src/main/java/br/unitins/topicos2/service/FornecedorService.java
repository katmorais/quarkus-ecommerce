package br.unitins.topicos2.service;

import java.util.List;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {

    // recursos basicos
    List<FornecedorResponseDTO> getAll(int page, int pageSize);

    FornecedorResponseDTO findById(Long id);

    FornecedorResponseDTO create(@Valid FornecedorDTO dto);

    FornecedorResponseDTO update(Long id, FornecedorDTO dto);

    void delete(Long id);

    // recursos extras

    List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}