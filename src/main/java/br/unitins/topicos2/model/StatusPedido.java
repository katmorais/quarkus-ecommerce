package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPedido {
  
    PENDENTE(1, "Pendete"), 
    PROCESSAMENTO(2, "Processamento"),
    ENVIADO(3, "Enviado"),
    ENTREGUE(4, "Entregue");
   
    private int id;
    private String label;

    StatusPedido(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static StatusPedido valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for(StatusPedido perfil : StatusPedido.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}
