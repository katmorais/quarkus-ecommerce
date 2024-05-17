package br.unitins.topicos2.model.jpaconverter;

import br.unitins.topicos2.model.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Sexo prioridade) {
        return prioridade == null ? null : prioridade.getId();
    }

    @Override
    public Sexo convertToEntityAttribute(Integer id) {
        return Sexo.valueOf(id);
    }
    
}