package br.unitins.topicos2.model;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Cupom extends DefaultEntity{
    private String codigo;
    private Double valorDesconto;
    private Date validade;
    private Boolean statusCupom;

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public double getValorDesconto() {
        return valorDesconto;
    }
    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
    public Boolean getStatusCupom() {
        return statusCupom;
    }
    public void setStatusCupom(Boolean statusCupom) {
        this.statusCupom = statusCupom;
    }
    public Date getValidade() {
        return validade;
    }
    public void setValidade(Date validade) {
        this.validade = validade;
    }
   
    
}
