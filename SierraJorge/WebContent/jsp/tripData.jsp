<jsp:useBean id="trip" class="uo.sdi.model.Trip" scope="request" />
<div class="w3-row">
	<div class="w3-container w3-third">
		<h3>Origen</h3>
		<p>${trip.departure.city} - ${trip.departure.state}, ${trip.departure.country}</p>
		<p>${trip.departure.address} ${trip.departure.zipCode} (${trip.departure.waypoint.lat}, ${trip.departure.waypoint.lon})</p>

		<h3>Destino</h3>
		<p>${trip.destination.city} - ${trip.destination.state}, ${trip.destination.country}</p>
		<p>${trip.destination.address} ${trip.destination.zipCode} (${trip.destination.waypoint.lat}, ${trip.destination.waypoint.lon})</p>

		<h3>Salida</h3>
		<p>${trip.departureDate}</p>

		<h3>Llegada</h3>
		<p>${trip.arrivalDate}</p>

		<h3>Plazo de inscripción</h3>
		<p>${trip.closingDate}</p>
	</div>
	<div class="w3-container w3-third">

		<h3>Plazas disponibles</h3>
		<p>${trip.availablePax} de ${trip.maxPax}</p>

		<h3>Coste estimado</h3>
		<p>${trip.estimatedCost }</p>

		<h3>Estado</h3>
		<p>${trip.status}</p>

		<h3>Comentario</h3>
		<p>${trip.comments}</p>
	</div>
</div>