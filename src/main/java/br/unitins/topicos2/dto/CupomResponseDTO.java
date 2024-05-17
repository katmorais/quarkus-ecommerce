package br.unitins.topicos2.dto;

import java.util.Date;

import br.unitins.topicos2.model.Cupom;


public record CupomResponseDTO (
    Long id,
    String codigo,
    Double valorDesconto,
    Date Validade,
    Boolean statusCupom
    
){
      public static CupomResponseDTO valueOf(Cupom cupom) {
        return new CupomResponseDTO(
                cupom.getId(),
                cupom.getCodigo(),
                cupom.getValorDesconto(),
                cupom.getValidade(),
                cupom.getStatusCupom());
               
    }   
}
