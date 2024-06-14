package br.unitins.topicos2.dto;

public record CheckoutDTO (
        String address,
        String cardNumber,
        String expiryDate,
        String cvv
) {}
