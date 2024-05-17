package br.unitins.topicos2.service;

import java.util.List;
import br.unitins.topicos2.dto.PessoaDTO;
import br.unitins.topicos2.dto.PessoaResponseDTO;
import jakarta.validation.Valid;

public interface PessoaService {

    // recursos basicos
    List<PessoaResponseDTO> getAll(int page, int pageSize);

    PessoaResponseDTO findById(Long id);

    PessoaResponseDTO create(@Valid PessoaDTO dto);

    PessoaResponseDTO update(Long id, PessoaDTO dto);

    void delete(Long id);

    // recursos extras

    List<PessoaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);

}
