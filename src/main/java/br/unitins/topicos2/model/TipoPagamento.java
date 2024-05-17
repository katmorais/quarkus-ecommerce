package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPagamento {
  
    ADMINISTRADOR(1, "Administrador"), 
    FUNCIONARIO(2, "Funcionario"),
    CLIENTE(3, "Cliente");

    private int id;
    private String label;
   
    TipoPagamento(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    
    public static TipoPagamento valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for(TipoPagamento perfil : TipoPagamento.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}