package br.unitins.topicos2.dto;

import java.util.Date;

public record CupomDTO (
    String codigo,
    Double valorDesconto,
    Date validade,
    Boolean statusCupom
    
){}

