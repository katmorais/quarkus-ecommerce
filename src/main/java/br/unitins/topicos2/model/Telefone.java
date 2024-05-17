package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity {
    private Long codigoArea;
    private Long numero;

    public Telefone() {
    }

    public Telefone(Long codigoArea, Long numero) {
        this.codigoArea = codigoArea;
        this.numero = numero;
    }

    public Long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
   

}
