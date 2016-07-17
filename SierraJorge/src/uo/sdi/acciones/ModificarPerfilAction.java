package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;

/**
 * Acción para modificar el perfil. Comprueba que todos los datos sean correctos.
 * Si algun dato no es introducido no genera un cambio en el perfil.
 * @author Jorge
 *
 */
public class ModificarPerfilAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {

	Log.trace("Comenzado modificación de perfil.");

	String login = (String) request.getParameter("login"),
	    nombre = (String) request.getParameter("nombreMod").trim(), 
	    apellidos = (String) request.getParameter("apellidoMod").trim(), 
	    email = (String) request.getParameter("emailMod").trim(),
	    pass = (String) request.getParameter("claveActual").trim(),
	    pass1 = (String) request.getParameter("claveMod1").trim(),
	    pass2 = (String) request.getParameter( "claveMod2").trim();

	boolean cambiaPass = (!(pass1.isEmpty() && pass2.isEmpty()));

	if (nombre.isEmpty() && apellidos.isEmpty() && email.isEmpty()
		&& !cambiaPass) {
	    request.setAttribute("warningMessage",
		    "No se han realizado cambios en su perfil.");
	    Log.debug("El usuario no se modifica porque no hay cambios.");
	    return "EXITO";
	}

	UserDao dao = PersistenceFactory.newUserDao();

	User user = dao.findByLogin(login);
	if (user == null) {
	    String errorMessage = "No se encuentra el perfil con nombre de usuario ["
		    + login + "].";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	// Modificar contraseña
	if (cambiaPass) {
	    if (!pass1.equals(pass2)) {
		String errorMessage = "Las contraseñas no coinciden.";
		Log.debug(errorMessage);
		request.setAttribute("errorMessage", errorMessage);
		return "FRACASO";
	    }
	    if (!user.getPassword().equals(pass)) {
		String errorMessage = "Las contraseña actual no es correcta.";
		Log.debug(errorMessage);
		request.setAttribute("errorMessage", errorMessage);
		return "FRACASO";
	    }
	    user.setPassword(pass1);
	}

	// Modificar email
	if (!email.isEmpty())
	    user.setEmail(email);

	// Modificar apellidos
	if (!apellidos.isEmpty())
	    user.setSurname(apellidos);

	// Modificar nombre
	if (!nombre.isEmpty())
	    user.setName(nombre);

	if (dao.update(user) == 0) {
	    String errorMessage = "No se ha podido modificar su perfil.";
	    Log.error("No se ha modificado el perfil.");
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	String successMessage = "Ha modificado con exito su perfil.";
	request.setAttribute("successMessage", successMessage);
	return "EXITO";
    }

}
