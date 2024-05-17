package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.ItemCompra;

public record ItemCompraResponseDTO(
    Integer quantidade,
    Double precoUnitario,
    Long idProduto,
    String nome
) { 
    public static ItemCompraResponseDTO valueOf(ItemCompra item){
        return new ItemCompraResponseDTO(
            item.getQuantidade(), 
            item.getPrecoUnitario(),
            item.getCamiseta().getId(),
            item.getCamiseta().getNome());
    }

    public static List<ItemCompraResponseDTO> valueOf(List<ItemCompra> item) {
       return item.stream().map(i -> ItemCompraResponseDTO.valueOf(i)).toList();
    }

}
