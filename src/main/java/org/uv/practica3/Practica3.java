package org.uv.practica3;

import java.sql.Connection;
import org.uv.practica3.DAL.DTO.UserDTO;
import org.uv.practica3.DAL.DAO.UserDAO;
import org.uv.practica3.BLL.Service.UserService;
import org.uv.practica3.tools.InputHandler;
import org.uv.practica3.tools.TerminalHelper;
import org.uv.practica3.tools.MySQLConn;

import java.util.List;

/**
 *
 * @author qinux
 */
public class Practica3 {

    public static void main(String[] args) {

        // * Conseguimos la instancia y conexion a la BD usando Singleton.
        MySQLConn dbInstance = MySQLConn.getInstance();
        Connection con = dbInstance.getConnection();

        // Usamos con para poder instanciar el DAO y este para el Servicio
        UserDAO userDAO = new UserDAO(con);
        UserService userService = new UserService(userDAO);

        // Herramientas para la interfaz de usuario.
        InputHandler input = new InputHandler();
        TerminalHelper term = new TerminalHelper();

        boolean flagMenu = true;
        boolean flagUpdate = true;

        while (flagMenu) {
            term.start();
            // START - MENU
            System.out.print("#################################################################\n"
                                + "#\t\t\t\t\t\t\t\t#\n"
                                + "#\t\t ██████╗██████╗ ██╗   ██╗██████╗ \t\t#\n"
                                + "#\t\t██╔════╝██╔══██╗██║   ██║██╔══██╗\t\t#\n"
                                + "#\t\t██║     ██████╔╝██║   ██║██║  ██║\t\t#\n"
                                + "#\t\t██║     ██╔══██╗██║   ██║██║  ██║\t\t#\n"
                                + "#\t\t╚██████╗██║  ██║╚██████╔╝██████╔╝\t\t#\n"
                                + "#\t\t ╚═════╝╚═╝  ╚═╝ ╚═════╝ ╚═════╝ \t\t#\n"
                                + "#\t\t                                 \t\t#\n");
            System.out.print("#\t\t\t\t\t\t\t\t#\n");
            System.out.print("#  [1] - CREAR un nuevo recurso en la base de datos;            #\n");
            System.out.print("#  [2] - LISTAR todos los recursos de la base de datos;         #\n");
            System.out.print("#  [3] - ACTUALIZAR un recurso de la base de datos;             #\n");
            System.out.print("#  [4] - ELIMINAR un recurso de la base de datos;               #\n");
            System.out.print("#  [n] - SALIR de la aplicacion;                                #\n");
            System.out.print("#\t\t\t\t\t\t\t\t#\n#\t\t\t\t\t\t\t\t#\n");
            System.out.print("#################################################################\n\n\n");

            String menu = input.isStr("- Selecciona una opcion: ");
            // END - MENU
            switch (menu) {
                case "1":
                    String nombre = input.isStr("Ingresa el nombre del usuario: ");
                    String direccion = input.isStr("Ingresa la direccion: ");
                    String telefono = input.isStr("Ingresa el numero de telefono: ");

                    UserDTO dto = new UserDTO(0, nombre, direccion, telefono);
                    userService.create(dto);
                    break;
                case "2":
                    List users = userService.read();
                    for (Object user : users) {
                        UserDTO userdto = (UserDTO) user;
                        System.out.println("\n["+userdto.getClave()+"]");
                        System.out.println("Nombre:\t\t"+userdto.getNombre());
                        System.out.println("Direccion:\t"+userdto.getDireccion());
                        System.out.println("Telefono:\t"+userdto.getTelefono());
                    }
                    input.isLine("\nPresione cualquier tecla para continuar...");
                    term.start();
                    break;
                case "3":
                    int claveUsuario = input.isInt("ingrese la clave del usuario que desea actualizar: ");
                    List updateList = userService.read();
                    for (Object user : updateList) {
                        UserDTO userdto = (UserDTO) user;
                        if (userdto.getClave() == claveUsuario) {
                            while (flagUpdate) {
                                System.out.println("Que valor desea editar:");
                                System.out.println("[1] Nombre: " + userdto.getNombre());
                                System.out.println("[2] Direccion: " + userdto.getDireccion());
                                System.out.println("[3] Telefono: " + userdto.getTelefono());
                                String property = input.isStr("\nElija una opcion: ");

                                switch (property) {
                                    case "1":
                                        String nuevoNombre = input.isStr("Escriba el nuevo valor: ");
                                        userdto.setNombre(nuevoNombre);
                                        flagUpdate = false;
                                        break;
                                    case "2":
                                        String nuevaDireccion = input.isStr("Escriba el nuevo valor: ");
                                        userdto.setDireccion(nuevaDireccion);
                                        flagUpdate = false;

                                        break;
                                    case "3":
                                        String nuevoTelefono = input.isStr("Escriba el nuevo valor: ");
                                        userdto.setTelefono(nuevoTelefono);
                                        flagUpdate = false;

                                        break;
                                    default:
                                        System.out.println("Opcion Invalida");
                                }
                            }

                        }
                        userService.update(userdto);

                    }
                    break;
                case "4":
                    claveUsuario = input.isInt("\t\t\t¡PRECAUCION!\nIngresa la clave del usuario: ");

                    userService.delete(claveUsuario);
                    break;
                case "n":
                    flagMenu = false;
                    System.out.println("Finalizando CRUD...");

                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }
}
