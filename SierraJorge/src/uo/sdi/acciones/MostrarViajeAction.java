package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alb.util.log.Log;
import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
/**
 * Acción para mostrar un viaje.
 * Carga tanto el viaje como información relacionada con él:
 * * Viaje
 * * Promotor
 * * Peticiones
 * * Asientos
 * 
 * @author Jorge
 *
 */
public class MostrarViajeAction implements Accion {

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	
	Long id = Long.parseLong((String) request.getParameter("id"));
	
	if(id==null)
	    return "FRACASO";
	Trip trip = PersistenceFactory.newTripDao().findById(id);
	if(trip == null){
	    Log.error("No se ha encontrado viaje.");
	    return "FRACASO";
	}
	request.setAttribute("trip", trip);
	User promotor = PersistenceFactory.newUserDao().findById(
		trip.getPromoterId());
	if (promotor == null) {
	    Log.error("No se ha encontrado promotor.");
	    return "FRACASO";
	}
	request.setAttribute("pro", promotor);

	SeatDao seatDao = PersistenceFactory.newSeatDao();

	
	List<Seat> asientos = seatDao.findByTrip(id);
	List<User> usuarios = new LinkedList<>();

	for (Seat seat : asientos) {
	    usuarios.add(PersistenceFactory.newUserDao()
		    .findById(seat.getUserId()));
	}
	Log.debug("Se han encontrado " + usuarios.size() 
		+ " pasajeros aceptados para este viaje.");
	request.setAttribute("asientos", asientos);
	request.setAttribute("pasajeros", usuarios);
	
	List<Application> peticiones = PersistenceFactory.newApplicationDao().findByTripId(id);
	
	request.setAttribute("peticiones", peticiones);


	return "EXITO";
    }

}
