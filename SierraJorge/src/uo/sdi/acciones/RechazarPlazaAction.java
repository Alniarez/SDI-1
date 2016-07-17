package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;

/**
 * Esta accion cancela la petición como compañero de viaje si se cumplen todos
 * los requisitos necesarios.
 * 
 * Para ello se elimina la petición y se crea un asiento.
 * @author Jorge
 *
 */
public class RechazarPlazaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Iniziando rechazo de plaza.");
	
	String idViaje = request.getParameter("idViaje"),
		idPasajero = request.getParameter("idUsuario");
	
	Long tripId = Long.parseLong(idViaje),
		userId = Long.parseLong(idPasajero);
	
	Seat seat = new Seat();
	seat.setTripId(tripId);
	seat.setUserId(userId);
	seat.setStatus(SeatStatus.EXCLUDED);
	//seat.setComment(comment);
		
	Trip trip = PersistenceFactory.newTripDao().findById(tripId);
	if(new Date().after(trip.getDepartureDate())){
	    String errorMessage = "La fecha del viaje ya ha pasado.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	ApplicationDao applicationDao = PersistenceFactory.newApplicationDao();
	SeatDao seatDao = PersistenceFactory.newSeatDao();
	
	Long[] longs = {userId, tripId};
	applicationDao.delete(longs);
	
	seatDao.save(seat);
	
	String successMessage = "Ha rechazado la petición con éxito.";
	Log.info(successMessage);
	request.setAttribute("successMessage", successMessage);
	
	return "EXITO";
    }

}
