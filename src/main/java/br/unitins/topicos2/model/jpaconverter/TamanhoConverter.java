package br.unitins.topicos2.model.jpaconverter;

import br.unitins.topicos2.model.Tamanho;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TamanhoConverter implements AttributeConverter<Tamanho, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Tamanho tamanho) {
        return tamanho.getId();
    }

    @Override
    public Tamanho convertToEntityAttribute(Integer id) {
        return Tamanho.valueOf(id);
    }
   
}