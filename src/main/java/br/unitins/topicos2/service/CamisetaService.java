package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CamisetaDTO;
import br.unitins.topicos2.dto.CamisetaResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public interface CamisetaService {

  // recursos basicos
      List<CamisetaResponseDTO> getAll();

      CamisetaResponseDTO findById(Long id);
  
      CamisetaResponseDTO create(@Valid CamisetaDTO dto) throws ConstraintViolationException;
  
      CamisetaResponseDTO update(Long id, CamisetaDTO dto);
  
      void delete(Long id);
  
      // recursos extras
  
      List<CamisetaResponseDTO> findByNome(String nome);
  
      long count();
  

}