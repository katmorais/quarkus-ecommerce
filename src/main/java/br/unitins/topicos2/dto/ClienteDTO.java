package br.unitins.topicos2.dto;

import java.time.LocalDate;

public record ClienteDTO(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    String username,
    String senha,
    Long idNaturalidade
) { }
