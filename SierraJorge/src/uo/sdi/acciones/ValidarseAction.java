package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

/**
 * Acción para validarse.
 * Comprubea el nombre y la contraseña.
 * 
 *  TODO ¿Comprobar estado del usuario? 
 *  (¿Un usuario bloqueado no puede entrar o no puede solicitar viajes?)
 *  
 * @author Jorge
 *
 */
public class ValidarseAction implements Accion {
    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Iniciando validación de usuario.");

	String resultado = "EXITO";

	String nombreUsuario = request.getParameter("nombreUsuarioLogin");
	String claveUsuario = request.getParameter("claveUsuarioLogin");

	if (nombreUsuario == null || claveUsuario == null) {
	    return "FRACASO";
	}

	HttpSession session = request.getSession();

	// Si no hay usuario en sesión
	if (session.getAttribute("user") == null) {
	    UserDao dao = PersistenceFactory.newUserDao();
	    User user = dao.validateLogin(nombreUsuario, claveUsuario);

	    if (user != null) {
		    session.setAttribute("user", user);
		    int contador = Integer.parseInt((String) request
			    .getServletContext().getAttribute("contador"));
		    request.getServletContext().setAttribute("contador",
			    String.valueOf(contador + 1));
		    Log.info("El usuario [%s] ha iniciado sesión",
			    nombreUsuario);
		    String successMessage = "Ha iniciado sesión con éxito. Bienvenido "
			    + user.getName() + ".";
		    request.setAttribute("successMessage", successMessage);
		} else {
		    session.invalidate();
		    String errorMessage = "El usuario ["
			    + nombreUsuario
			    + "] no está registrado o la contraseña es incorrecta.";
		    Log.info(errorMessage);
		    request.setAttribute("errorMessage", errorMessage);
		    resultado = "FRACASO";
		}
	}
	// Hay usuario en sesión
	else if (!nombreUsuario.equals(session.getAttribute("user"))) {
	    String errorMessage = "Se ha intentado iniciar sesión como ["
		    + nombreUsuario + "] teniendo la sesión iniciada como ["
		    + ((User) session.getAttribute("user")).getLogin() + "]";
	    Log.info(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    session.invalidate();
	    resultado = "FRACASO";
	}
	return resultado;
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
