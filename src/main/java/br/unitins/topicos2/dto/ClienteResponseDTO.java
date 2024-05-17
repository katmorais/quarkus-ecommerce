package br.unitins.topicos2.dto;

import java.time.LocalDate;
import br.unitins.topicos2.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String cpf,
        String nome,
        LocalDate dataNascimento,
        String username,
        String senha,
        CidadeResponseDTO naturalidade) {

    public static ClienteResponseDTO valueOf(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getPessoa().getCpf(),
                cliente.getPessoa().getNome(),
                cliente.getPessoa().getDataNascimento(),
                (cliente.getPessoa().getUsuario() == null ? null : cliente.getPessoa().getUsuario().getUsername()),
                (cliente.getPessoa().getUsuario() == null ? null : cliente.getPessoa().getUsuario().getSenha()),
                CidadeResponseDTO.valueOf(cliente.getNaturalidade()));
    }

}