package org.uv.practica3.BLL.Service;

import org.uv.practica3.DAL.POJO.User;
import org.uv.practica3.DAL.DTO.UserDTO;
import org.uv.practica3.DAL.converter.UserConverter;
import org.uv.practica3.DAL.DAO.UserDAO;





import java.util.List;

/**
 *
 * @author qinux
 */
public class UserService {
    private UserDAO userDAO;
    
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /**
     * > Crea un registro de un usuario en la BD
     * @param dto los datos ingresados por el usuario.
     */
    public void create(UserDTO dto) {
        User entity = new UserConverter().toEntity(dto);
        userDAO.store(entity);
    }
    
    /**
     * > Lista los registros de usuarios.
     * @return una lista con todos los usuarios.
     */
    public List<UserDTO> read(){
        List users = userDAO.aquire();
        List usersDTO = new UserConverter().toDTOList(users);
        return usersDTO;
    }
    
    /**
     * Actualiza un usuario de la BD.
     * @param dto datos ingresados por el usuario.
     */
    public void update(UserDTO dto) {
        User entity = new UserConverter().toEntity(dto);
        userDAO.shift(entity);
    }
    
    /**
     * Elimina un registro de un usuario.
     * @param dto datos ingresados por el usuario.
     */
    public void delete(int clave) {
        userDAO.remove(clave);
    }
}
