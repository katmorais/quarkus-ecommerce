package br.unitins.topicos2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCompra extends DefaultEntity {

    private Integer quantidade;
    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_camiseta")
    private Camiseta camiseta;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPreco(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Camiseta getCamiseta() {
        return camiseta;
    }

    public void setCamiseta(Camiseta camiseta) {
        this.camiseta = camiseta;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

}

