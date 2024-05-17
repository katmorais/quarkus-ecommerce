package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CupomDTO;
import br.unitins.topicos2.dto.CupomResponseDTO;
import jakarta.validation.Valid;

public interface CupomService {
       // recursos basicos
       List<CupomResponseDTO> getAll(int page, int pageSize);

       CupomResponseDTO findById(Long id);
   
       CupomResponseDTO create(@Valid CupomDTO dto);
   
       CupomResponseDTO update(Long id, CupomDTO dto);
   
       void delete(Long id);
   
       // recursos extras
   
       List<CupomResponseDTO> findByNome(String nome, int page, int pageSize);
   
       long count();
   
       long countByNome(String nome);

       Object getStatus(Long id);
   
   }