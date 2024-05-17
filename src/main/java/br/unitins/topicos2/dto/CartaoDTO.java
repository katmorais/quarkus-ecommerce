package br.unitins.topicos2.dto;



import jakarta.validation.constraints.NotBlank;


public record CartaoDTO(
    @NotBlank(message = "O campo precisa ser preenchido.")
    String nome,
    String numeroCartao,
    String dataVencimento
) {
    
}
