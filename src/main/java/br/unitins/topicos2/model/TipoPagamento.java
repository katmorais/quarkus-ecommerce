package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPagamento {

    ADMINISTRADOR(1, "Administrador"),
    FUNCIONARIO(2, "Funcionario"),
    CLIENTE(3, "Cliente");

    private int id;
    private String tipoPagamento;

    TipoPagamento(int id, String tipoPagamento) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        for (TipoPagamento tipopagamento : TipoPagamento.values()) {
            if (tipopagamento.id == id)
                return tipopagamento;
        }
        throw new IllegalArgumentException("id tipopagamento inválido.");
    }

}