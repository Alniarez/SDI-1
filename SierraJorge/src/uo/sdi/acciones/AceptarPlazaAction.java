package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import alb.util.log.Log;

/**
 * Esta accion aceta a un usuario como compañero de viaje si se cumplen todos
 * los requisitos necesarios.
 * 
 * Para ello se elimina la petición y se crea un asiento. Se actualizan los
 * asientos disponibles del viaje.
 * 
 * @author Jorge
 * 
 */
public class AceptarPlazaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Aceptando plaza.");

	String idViaje = request.getParameter("idViaje"),
		idPasajero = request.getParameter("idUsuario");

	Long tripId = Long.parseLong(idViaje),
		userId = Long .parseLong(idPasajero);

	Seat seat = new Seat();
	seat.setTripId(tripId);
	seat.setUserId(userId);
	seat.setStatus(SeatStatus.ACCEPTED);
	// seat.setComment(comment);

	// Que el viaje no haya pasado
	Trip trip = PersistenceFactory.newTripDao().findById(tripId);
	if (new Date().after(trip.getDepartureDate())) {
	    String errorMessage = "La fecha del viaje ya ha pasado.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	// QUe el estado del viaje sea el adecuado
	if (trip.getStatus().equals(TripStatus.CANCELLED)
		|| trip.getStatus().equals(TripStatus.DONE)) {
	    String errorMessage = "El viaje no puede aceptar nuevos pasajeros en este estado.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	// Que el viaje no esté lleno
	if (trip.getAvailablePax() == 0) {
	    String errorMessage = "No hay plazas libres en el viaje.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	ApplicationDao applicationDao = PersistenceFactory.newApplicationDao();
	SeatDao seatDao = PersistenceFactory.newSeatDao();

	// Actualizar asientos
	trip.setAvailablePax(trip.getAvailablePax() - 1);
	PersistenceFactory.newTripDao().update(trip);

	// Borrar peticion
	Long[] longs = { userId, tripId };
	applicationDao.delete(longs);

	// Guardar asiento
	seatDao.save(seat);

	String successMessage = "Ha aceptado la petición con éxito.";
	Log.info(successMessage);
	request.setAttribute("successMessage", successMessage);

	return "EXITO";
    }

}
