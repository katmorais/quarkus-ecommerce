package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tamanho {
    P(1, "P"), 
    M(2, "M"),
    G(3, "G"), 
    GG(4, "GG");
   
    private int id;
    private String label;

    Tamanho(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Tamanho valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for(Tamanho perfil : Tamanho.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        } 
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}



