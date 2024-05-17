package br.unitins.topicos2.dto;

import java.util.List;

public record CompraDTO (
    // FormaPagamento pagamento,
    // EnderecoDTO endereco,
    List<ItemCompraDTO> itens
) {

}
