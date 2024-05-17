package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Cargo {
    ADMINISTRADOR(1, "Administrador"),
    GERENTE(2, "Gerente"),
    CLIENTE(3, "Cliente");

    private int id;
    private String label;

    Cargo(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Cargo valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for (Cargo perfil : Cargo.values()) {
            if (id.equals(perfil.getId()))
                return perfil;
        }
        throw new IllegalArgumentException("Id inválido:" + id);
    }

}
