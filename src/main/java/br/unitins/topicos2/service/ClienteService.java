package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.ClienteDTO;
import br.unitins.topicos2.dto.ClienteResponseDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface ClienteService {

    // recursos basicos
    List<ClienteResponseDTO> getAll(int page, int pageSize);

    ClienteResponseDTO findById(Long id);

    ClienteResponseDTO create(@Valid ClienteDTO dto);

    ClienteResponseDTO update(Long id, ClienteDTO dto);

    void delete(Long id);

    // recursos extras

    List<ClienteResponseDTO> findByNome(String nome);

    UsuarioResponseDTO findByUsernameAndSenha(String username, String senha);

    long count();

    void alterarSenha(ClienteDTO dto);
}
