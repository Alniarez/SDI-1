package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

/**
 * Acción para crear una cuenta.
 * Comprueba que todos los campos sean correctos.
 * @author Jorge
 *
 */
public class CrearCuenta implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Inicianda creación de cuenta.");

	String nombreUsuario = request.getParameter("nombreUsuarioRegister")
		.trim();
	String nombre = request.getParameter("nombreRegister").trim();
	String apellidos = request.getParameter("apellidoRegister").trim();
	String email = request.getParameter("emailRegister").trim();

	String claveRegister1 = request.getParameter("claveRegister1").trim();
	String claveRegister2 = request.getParameter("claveRegister2").trim();

	if (nombreUsuario == null || nombre == null || apellidos == null
		|| email == null || claveRegister1 == null
		|| claveRegister2 == null)
	    return "FRACASO";

	if (!claveRegister1.equals(claveRegister2)) {
	    String errorMessage = "Las contraseñas no coinciden.";
	    Log.info(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	UserDao dao = PersistenceFactory.newUserDao();

	if (dao.findByLogin(nombreUsuario) != null) {
	    String errorMessage = "El nombre de usuario [" + nombreUsuario
		    + "] ya está en uso.";
	    Log.info(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	User user = new User().setLogin(nombreUsuario).setName(nombre)
		.setSurname(apellidos).setEmail(email)
		.setPassword(claveRegister1).setStatus(UserStatus.ACTIVE);

	Long id = dao.save(user);

	//System.out.println("Creado con id: " + id);
	Log.info("Se ha creado un nuevo usuario con id [" + id + "]");

	String successMessage = "Ha creado su cuenta con éxito. "
		+ "Inicie sesión para acceder a los servicios.";
	request.setAttribute("successMessage", successMessage);

	return "EXITO";
    }

}
