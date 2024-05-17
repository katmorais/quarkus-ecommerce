package br.unitins.topicos2.model.jpaconverter;

import br.unitins.topicos2.model.Cargo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CargoConveter implements AttributeConverter<Cargo, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Cargo prioridade) {
        return prioridade == null ? null : prioridade.getId();
    }

    @Override
    public Cargo convertToEntityAttribute(Integer id) {
        return Cargo.valueOf(id);
    }

}