package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.Waypoint;
import alb.util.log.Log;

/**
 * Esta clase realiza las comprobaciones comunes para validar un viaje.
 * En la creación y modificación se utiliza una clase que extiende esta.
 * 
 * @author Jorge
 *
 */
public abstract class ViajeAccion implements Accion{
    
    abstract void inicio(HttpServletRequest request);
    /** 
     * @return El objeto Trip del modelo de dominio que se usa en la acción.
     */
    abstract Trip viajeAccion();
    
    /**
     * @return El estado del viaje tras la accion.
     */
    abstract TripStatus status();
    
    /**
     * @return Las plazas disponibles tras la accion.
     */
    abstract Integer pax();
    

    /**
     * Realiza la acción asociada a la accion. (<i>bruh</i>).
     * 
     * @param request
     * @return Codigo para mapeo
     */
    abstract String accion(HttpServletRequest request);
    
    Trip trip;

    @Override
    public String execute(HttpServletRequest request,
	    HttpServletResponse response) {
	
	String direccionOrigen = request.getParameter("direccionOrigen"),
		ciudadOrigen = request.getParameter("ciudadOrigen"),
		estadoOrigen = request.getParameter("estadoOrigen"),
		paisOrigen = request.getParameter("paisOrigen"),
		zipOrigen = request.getParameter("zipOrigen"),
		latOrigen = request.getParameter("latOrigen"),
		lonOrigen = request.getParameter("lonOrigen"),
		direccionDestino = request.getParameter("direccionDestino"),
		ciudadDestino = request.getParameter("ciudadDestino"),
		estadoDestino = request.getParameter("estadoDestino"),
		paisDestino = request.getParameter("paisDestino"),
		zipDestino = request.getParameter("zipDestino"),
		latDestino = request.getParameter("latDestino"),
		lonDestino = request.getParameter("lonDestino"),
		salida = request.getParameter("salida"),
		salidaH = request.getParameter("salidaH"),
		llegada = request.getParameter("llegada"),
		llegadaH = request.getParameter("llegadaH"),
		cierre = request.getParameter("cierre"),
		plazas = request.getParameter("plazas"),
		coste = request.getParameter("coste"),
		comentario = request.getParameter("comentario"),
		idPromotor = request.getParameter("idPromotor");

	// Campos no vacios
	if (direccionOrigen == null || ciudadOrigen == null
		|| estadoOrigen == null || paisOrigen == null
		|| zipOrigen == null || direccionDestino == null
		|| ciudadDestino == null || estadoDestino == null
		|| paisDestino == null || zipDestino == null || salida == null
		|| salidaH == null || llegada == null || llegadaH == null
		|| cierre == null || plazas == null || coste == null) {
	    String errorMessage = "Falta alguno de los datos necesarios.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	if (direccionOrigen.isEmpty() || ciudadOrigen.isEmpty()
		|| estadoOrigen.isEmpty() || paisOrigen.isEmpty()
		|| zipOrigen.isEmpty() || direccionDestino.isEmpty()
		|| ciudadDestino.isEmpty() || estadoDestino.isEmpty()
		|| paisDestino.isEmpty() || zipDestino.isEmpty()
		|| salida.isEmpty() || salidaH.isEmpty() || llegada.isEmpty()
		|| llegadaH.isEmpty() || cierre.isEmpty() || plazas.isEmpty()
		|| coste.isEmpty()) {
	    String errorMessage = "Falta alguno de los datos necesarios.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	}

	// Comprobar coordenadas
	boolean noCoordenada = false;

	// Origen
	if (latOrigen == null) {
	    latOrigen = "0";
	    lonOrigen = "0";
	    noCoordenada = true;
	} else if (latOrigen.isEmpty()) {
	    latOrigen = "0";
	    lonOrigen = "0";
	    noCoordenada = true;
	}

	if (lonOrigen == null) {
	    latOrigen = "0";
	    lonOrigen = "0";
	    noCoordenada = true;
	} else if (lonOrigen.isEmpty()) {
	    latOrigen = "0";
	    lonOrigen = "0";
	    noCoordenada = true;
	}

	// Destino
	if (latDestino == null) {
	    latDestino = "0";
	    lonDestino = "0";
	    noCoordenada = true;
	} else if (latDestino.isEmpty()) {
	    latDestino = "0";
	    lonDestino = "0";
	    noCoordenada = true;
	}

	if (lonDestino == null) {
	    latDestino = "0";
	    lonDestino = "0";
	    noCoordenada = true;
	} else if (lonDestino.isEmpty()) {
	    latDestino = "0";
	    lonDestino = "0";
	    noCoordenada = true;
	}

	if (noCoordenada) {
	    String warningMessage = "Falta alguna coodenada. Se establece la coordenada 0,0 para esa localización.";
	    Log.warn(warningMessage);
	    request.setAttribute("warningMessage", warningMessage);
	}

	// Comprobar fechas
	String cadenaFechaLlegada = llegada + "/" + llegadaH;
	String cadenaFechaSalida = salida + "/" + salidaH;

	Date fechaLlegada, fechaSalida, fechaCierre, today = new Date();

	SimpleDateFormat formatoConHora = new SimpleDateFormat(
		"yyy-MM-dd/hh:mm");
	SimpleDateFormat formatoSinHora = new SimpleDateFormat("yyy-MM-dd");

	try {
	    fechaLlegada = formatoConHora.parse(cadenaFechaLlegada);
	    fechaSalida = formatoConHora.parse(cadenaFechaSalida);
	    fechaCierre = formatoSinHora.parse(cierre);
	} catch (ParseException e) {
	    String errorMessage = "Formato de fecha incorrecto.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	if (fechaSalida.after(fechaLlegada)) {
	    String errorMessage = "La fecha de salida es anterior a la fecha de llegada.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	if (fechaCierre.after(fechaSalida)) {
	    String errorMessage = "La fecha de cierre es porterior a la fecha de salida.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	if(fechaCierre.before(today) || fechaLlegada.before(today) || fechaLlegada.before(today)){
	    String errorMessage = "Debe usar fechas futuras.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	// Comprobar numeros
	int plazasViaje;

	try {
	    plazasViaje = Integer.parseInt(plazas);
	} catch (NumberFormatException e) {
	    String errorMessage = "Formato de plazas incorrecto.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	if (plazasViaje < 1) {
	    String errorMessage = "Debe ofrecer plazas en su viaje.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	double costeViaje;

	try {
	    costeViaje = Double.parseDouble(coste);
	} catch (NumberFormatException e) {
	    String errorMessage = "Formato de coste incorrecto.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}

	if (costeViaje < 0) {
	    String errorMessage = "El viaje no puede tener coste negativo";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	
	trip = viajeAccion();
	if(trip == null){
	    String errorMessage = "No se ha podido inicializar el viaje.";
	    Log.error(errorMessage);
	    request.setAttribute("errorMessage", errorMessage);
	    return "FRACASO";
	}
	    
	
	// Origen
	{
	    double lat = Double.parseDouble(latOrigen);
	    double lon = Double.parseDouble(lonOrigen);
	    Waypoint waypoint = new Waypoint(lat, lon);

	    AddressPoint addressPoint = new AddressPoint(direccionOrigen,
		    ciudadOrigen, estadoOrigen, paisOrigen, zipOrigen, waypoint);
	    trip.setDeparture(addressPoint);
	}
	// Destino
	{
	    double lat = Double.parseDouble(latDestino);
	    double lon = Double.parseDouble(lonDestino);
	    Waypoint waypoint = new Waypoint(lat, lon);

	    AddressPoint addressPoint = new AddressPoint(direccionDestino,
		    ciudadDestino, estadoDestino, paisDestino, zipDestino,
		    waypoint);
	    trip.setDestination(addressPoint);
	}

	trip.setArrivalDate(fechaLlegada);
	trip.setDepartureDate(fechaSalida);
	trip.setClosingDate(fechaCierre);
	trip.setMaxPax(plazasViaje);
	trip.setEstimatedCost(costeViaje);
	if (comentario != null)
	    trip.setComments(comentario);
	
	trip.setStatus(status());
	trip.setAvailablePax(pax());
	
	trip.setPromoterId(Long.parseLong(idPromotor));


	return accion(request);
    }

    

}
