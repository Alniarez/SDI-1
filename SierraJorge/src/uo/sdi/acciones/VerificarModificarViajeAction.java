package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;

import alb.util.log.Log;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.PersistenceFactory;

/**
 * Accion para verificar los datos de la modificación de un viaje.
 * Utiliza la validación comun a a los viajes de la clase abstracta ViajeAccion.
 * 
 * @author Jorge
 *
 */
public class VerificarModificarViajeAction extends ViajeAccion {

    String idViaje;
    
    @Override
    void inicio(HttpServletRequest request) {
	Log.trace("Iniciando validación de modificación de viaje.");
	idViaje = request.getParameter("viajeId");
    }

    @Override
    Trip viajeAccion() {
	Long id = Long.parseLong(idViaje);
	Trip trip = PersistenceFactory.newTripDao().findById(id);
	return trip;
    }

    @Override
    TripStatus status() {
	return trip.getStatus();
    }

    @Override
    Integer pax() {
	return trip.getAvailablePax();
    }

    @Override
    String accion(HttpServletRequest request) {
	if(1 == PersistenceFactory.newTripDao().update(trip)){
	    Log.debug("Se ha modificado con exito el viaje con id["+idViaje+"].");
	    request.setAttribute("successMessage", "Se ha modificado su viaje correctamente.");
	    return "EXITO";
	}else{
	    Log.debug("No se ha modificado con exito el viaje con id["+idViaje+"].");
	    request.setAttribute("errorMessage", "No se ha podido modificar el viaje.");

	    return "FRACASO";
	}
    }


}
