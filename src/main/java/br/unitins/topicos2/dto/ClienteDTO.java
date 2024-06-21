package br.unitins.topicos2.dto;

import java.time.LocalDate;

public record ClienteDTO(
        String nome,
        String username,
        String senha,
        String cpf,
        LocalDate dataNascimento,
        Long cidadeId,
        Long estadoId
) {
}
