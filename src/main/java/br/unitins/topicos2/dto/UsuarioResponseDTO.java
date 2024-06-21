package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Cargo;
import br.unitins.topicos2.model.Cliente;
import br.unitins.topicos2.model.Pessoa;

import static java.util.Objects.isNull;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String username,
        Cargo cargo) {
    public static UsuarioResponseDTO valueOf(Cliente cliente) {
        if (isNull(cliente) || isNull(cliente.getPessoa()) || isNull(cliente.getPessoa().getUsuario()))
            return null;

        return new UsuarioResponseDTO(
                cliente.getId(),
                cliente.getPessoa().getNome(),
                cliente.getPessoa().getUsuario().getUsername(),
                cliente.getCargo());
    }
}