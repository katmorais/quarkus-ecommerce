package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {

    PENDENTE(1, "Pendete"),
    PROCESSAMENTO(2, "Processamento"),
    ENVIADO(3, "Enviado"),
    ENTREGUE(4, "Entregue");

    private int id;
    private String statusPedido;

    StatusPedido(int id, String statusPedido) {
        this.id = id;
        this.statusPedido = statusPedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        for (StatusPedido statuspedido : StatusPedido.values()) {
            if (statuspedido.id == id)
                return statuspedido;
        }
        throw new IllegalArgumentException("id statuspedido inválido.");
    }

}
