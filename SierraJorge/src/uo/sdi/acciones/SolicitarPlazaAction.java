package uo.sdi.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

/**
 * Acción para solicitar plaza.
 * Si todos los requisitos se cimplen se crea una petición en la base de datos.
 * 
 * @author Jorge
 *
 */
public class SolicitarPlazaAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	Log.trace("Iniciando petición de plaza.");

	String idViaje = request.getParameter("idViaje"),
		idPasajero = request.getParameter("idUsuario");
	
	Long tripId = Long.parseLong(idViaje),
		userId = Long.parseLong(idPasajero);
	
	ApplicationDao dao = PersistenceFactory.newApplicationDao();
	
	Long[] longs = {userId, tripId};
	
	Application application = dao.findById(longs);
	
	if(application!=null){
	    String errorMessage = "Ya tienes una petición pendiente.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	Seat seat = PersistenceFactory.newSeatDao().findByUserAndTrip(userId, tripId);
	if(seat!=null){
	    String errorMessage = "Ya has sido aceptado o candelado en este viaje.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	Trip trip = PersistenceFactory.newTripDao().findById(tripId);
	
	if(new Date().after(trip.getClosingDate())){
	    String errorMessage = "La fecha de inscripción ya ha pasado.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	if(!trip.getStatus().equals(TripStatus.OPEN)){
	    String errorMessage = "El viaje no está abierto.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	if(trip.getAvailablePax()==0){
	    String errorMessage = "No hay plazas disponibles.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	
	
	
	application = new Application(userId, tripId);
	
	dao.save(application);
	
	String successMessage = "Se ha creado la petición con éxito.";
	Log.info(successMessage);
	request.setAttribute("successMessage", successMessage);
		
	return "EXITO";
    }

}
