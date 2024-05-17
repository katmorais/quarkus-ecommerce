package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
        Long id,
        @NotBlank(message = "O campo precisa ser preenchido.")
        String rua,
        @NotBlank(message = "O campo precisa ser preenchido.")
        String numero,
        @NotBlank(message = "O campo precisa ser preenchido.")
        String complemento,
        String bairro,
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
        String cep,
        @NotNull(message = "O campo precisa ser preenchido.")
        Long idCidade
) {

}