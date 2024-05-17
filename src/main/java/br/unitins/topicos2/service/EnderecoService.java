package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.EnderecoResponseDTO;
import jakarta.validation.Valid;

public interface EnderecoService {
   
      // recursos basicos
      List<EnderecoResponseDTO> getAll(int page, int pageSize);

      EnderecoResponseDTO findById(Long id);
  
      EnderecoResponseDTO create(@Valid EnderecoDTO dto);
  
      EnderecoResponseDTO update(Long id, EnderecoDTO dto);
  
      void delete(Long id);
  
      // recursos extras
  
      List<EnderecoResponseDTO> findByNome(String nome, int page, int pageSize);
  
      long count();
  
      long countByNome(String nome);
  
  }