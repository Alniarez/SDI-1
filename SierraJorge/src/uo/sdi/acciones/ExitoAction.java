package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Acción que siempre da éxito y no realiza ninguna otra función.
 * 
 * @author Jorge
 * 
 */
public class ExitoAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	return "EXITO";
    }

}
