package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

/**
 * Accion para verificar los datos de la creación de un viaje.
 * Utiliza la validación comun a a los viajes de la clase abstracta ViajeAccion.
 * 
 * @author Jorge
 *
 */
public class VerificarCrearViajeAction extends ViajeAccion {

    @Override
    void inicio(HttpServletRequest request) {
	Log.trace("Iniciando validación de creación de viaje.");
    }

    @Override
    Trip viajeAccion() {
	return new Trip();
    }

    @Override
    String accion(HttpServletRequest request) {
	Long id = PersistenceFactory.newTripDao().save(trip);
	if(id!=null){
	    Log.debug("Se ha creado un viaje con id [" + id + "].");
	    request.setAttribute("successMessage", "Se ha creado su viaje correctamente.");
	    return "EXITO";
	}
	
	Log.error("No se ha creado ningún viaje.");
	return "FRACASO";
    }

    @Override
    TripStatus status() {
	return TripStatus.OPEN;
    }

    @Override
    Integer pax() {
	return trip.getMaxPax();
    }
}
