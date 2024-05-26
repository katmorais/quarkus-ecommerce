package br.unitins.topicos2.model.jpaconverter;

import br.unitins.topicos2.model.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Integer>{
 
    @Override
    public Integer convertToDatabaseColumn(StatusPedido statusPedido) {
        return statusPedido.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Integer id) {
        return StatusPedido.valueOf(id);
    }

   
}