package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import static java.util.Objects.isNull;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Sexo {
  
    MASCULINO(1, "Masculino"), 
    FEMININO(2, "Feminino");
   
    private final int id;
    private final String descricao;

    Sexo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static Sexo getByDescricao(String categoria) {
        for(Sexo sexo : Sexo.values()) {
            if (categoria.equalsIgnoreCase(sexo.getDescricao())) return sexo;
        }
        throw new IllegalArgumentException("Descrição inválida:" + categoria);
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Sexo valueOf(Integer id) throws IllegalArgumentException {
        if (isNull(id)) return null;

        for(Sexo sexo : Sexo.values()) {
            if (id.equals(sexo.getId())) return sexo;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}
