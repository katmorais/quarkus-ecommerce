package br.unitins.topicos2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Cargo {
    ADMINISTRADOR(1, "Administrador"),
    GERENTE(2, "Gerente"),
    CLIENTE(3, "Cliente");

    private int id;
    private String cargo;

    Cargo(int id, String cargo) {
        this.id = id;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public static Cargo valueOf(Integer id) throws IllegalArgumentException {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.id == id)
                return cargo;
        }
        throw new IllegalArgumentException("id cargo inválido.");
    }

}
