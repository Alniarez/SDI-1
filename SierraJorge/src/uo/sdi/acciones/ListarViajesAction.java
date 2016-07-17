package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

/**
 * Acción para listar viajes disponibles, es decir, activos y que no hayan
 * pasado.
 * 
 * @author Jorge
 * 
 */
public class ListarViajesAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Iniciando listado de viajes activos.");

	List<Trip> viajes;

	try {
	    viajes = PersistenceFactory.newTripDao().findAllActive();

	    request.setAttribute("listaViajes", viajes);
	    Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
		    viajes.size());
	} catch (Exception e) {
	    Log.error("Algo ha ocurrido obteniendo lista de viajes");
	}
	return "EXITO";
    }

    @Override
    public String toString() {
	return getClass().getName();
    }

}
