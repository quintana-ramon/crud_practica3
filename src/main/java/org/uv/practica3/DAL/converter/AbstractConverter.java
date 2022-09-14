package org.uv.practica3.DAL.converter;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author qinux
 */
public abstract class AbstractConverter<E, D> {
    public abstract E toEntity(D dto);
    
    public abstract D toDTO(E entity);
    
    public List<E> toEntityList(List<D> dtos) {
        if(dtos == null)
            return null;
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }
    
    public List<D> toDTOList(List<E> entities) {
        if(entities == null)
            return null;
        return entities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
    }
}
