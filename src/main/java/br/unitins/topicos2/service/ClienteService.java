package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.ClienteDTO;
import br.unitins.topicos2.dto.ClienteResponseDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.model.Checkout;
import br.unitins.topicos2.model.Cliente;
import jakarta.validation.Valid;

public interface ClienteService {

    // recursos basicos
    List<ClienteResponseDTO> getAll(int page, int pageSize);

    Cliente findById(Long id);

    ClienteResponseDTO create(@Valid ClienteDTO dto);

    ClienteResponseDTO update(Long id, ClienteDTO dto);

    void delete(Long id);

    // recursos extras

    List<ClienteResponseDTO> findByNome(String nome);

    UsuarioResponseDTO findByUsernameAndSenha(String username, String senha);

    long count();

    void alterarSenha(ClienteDTO dto);

    List<Checkout> getCompras(Long id);
}
