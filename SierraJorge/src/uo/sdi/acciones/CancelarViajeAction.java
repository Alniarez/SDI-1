package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

/**
 * Acción para cancelar un viaje.
 * Modifica el estado del viaje sin eliminarlo de la base de datos.
 * @author Jorge
 *
 */
public class CancelarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Iniciando cancelación de viaje.");

	String idViaje = request.getParameter("id");
	Long id = Long.parseLong(idViaje);

	TripDao dao = PersistenceFactory.newTripDao();
	Trip trip = dao.findById(id);

	User user = (User) request.getSession().getAttribute("user");
	
	if (!trip.getPromoterId().equals(user.getId())) {
	    Log.error("Se ha intentado modificar el viaje de otro.");
	    return "FRACASO";
	}

	trip.setStatus(TripStatus.CANCELLED);

	int rows = dao.update(trip);
	System.err.println(rows);
	if (rows == 0) {
	    Log.error("No se ha podido modificar el viaje.");
	    return "FRACASO";
	}

	String successMessage = "Se modificado con exito el viaje.";
	Log.debug(successMessage);
	request.setAttribute("successMessage", successMessage);

	return "EXITO";
    }

}
