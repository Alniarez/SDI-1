package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;

/**
 * Accion para cerrar la sesión.
 * @author Jorge
 *
 */
public class CerrarSesionAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	request.getSession().invalidate();
	Log.trace("Cerrando sesión.");

	String successMessage = "Ha cerrado sesión.";
	request.setAttribute("successMessage", successMessage);

	return "EXITO";
    }

}
