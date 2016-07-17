package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

/**
 * Acción para ver el perfil se usuario.
 * Además carga los viajes en los que ha participado el usuario como historico,
 * para contorlar sus viajes y en los que participa.
 * 
 * @author Jorge
 *
 */
public class VerPerfilAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("iniciando vista de perfil.");
	
	User user = (User) request.getSession().getAttribute("user");
	
	List<Trip> viajes = PersistenceFactory.newTripDao().findWhenParticipated(user.getId());
	
	 request.setAttribute("listaViajes", viajes);
	
	return "EXITO";
    }

}
