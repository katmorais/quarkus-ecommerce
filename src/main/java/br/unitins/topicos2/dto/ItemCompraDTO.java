package br.unitins.topicos2.dto;

public record ItemCompraDTO(
        Long id,
        String nome,
        Integer quantidade,
        Double preco
) {

}

