package org.uv.practica3.DAL.converter;

import org.uv.practica3.DAL.POJO.User;
import org.uv.practica3.DAL.DTO.UserDTO;

/**
 *
 * @author qinux
 */
public class UserConverter extends AbstractConverter<User, UserDTO>{
    
    @Override
    public User toEntity(UserDTO dto) {
        return (dto.getClave() == 0) ? new User(0, dto.getNombre(), dto.getDireccion(), dto.getTelefono()) : new User(dto.getClave(), dto.getNombre(), dto.getDireccion(), dto.getTelefono());
    }
    
    @Override
    public UserDTO toDTO(User entity) {
        return new UserDTO(entity.getClave(), entity.getNombre(), entity.getDireccion(), entity.getTelefono());
    }
}
