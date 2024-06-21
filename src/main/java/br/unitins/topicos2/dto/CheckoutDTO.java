package br.unitins.topicos2.dto;

import java.util.List;

public record CheckoutDTO (
        String address,
        String cardNumber,
        String expiryDate,
        String cvv,
        List<ItemCompraDTO> items,
        Long idCliente
) {}
