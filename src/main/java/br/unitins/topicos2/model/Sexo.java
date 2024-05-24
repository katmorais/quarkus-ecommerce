package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
  
    MASCULINO(1, "Masculino"), 
    FEMININO(2, "Feminino");
   
    private final int id;
    private final String sexo;

    Sexo(int id, String sexo) {
        this.id = id;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public String getSexo() {
        return sexo;
    }

    public static Sexo valueOf(Integer id) throws IllegalArgumentException {
        for (Sexo sexo : Sexo.values()) {
            if (sexo.id == id)
                return sexo;
        }
        throw new IllegalArgumentException("id sexo inválido.");
    }

}
