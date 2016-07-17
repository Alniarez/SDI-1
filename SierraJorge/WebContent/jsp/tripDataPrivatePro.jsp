<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="w3-container w3-quarter">
	<h2>Control de promotor</h2>
	<a id="modificarViaje" href="modificarViaje" class="w3-btn w3-blue w3-large w3-padding">Modificar viaje</a>
	<a id="cancelarViaje" href="cancelarViaje?id=${param.id}" class="w3-btn w3-red w3-large w3-padding">Cancelar viaje</a>
</div>
<div class="w3-container w3-half">
	<h2>Pasajeros</h2>
	<table class="w3-table w3-striped">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Estado</th>
				<th>Comentario</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entry" items="${pasajeros}" varStatus="i">
				<tr id="item_${i.index}">
					<td>${entry.name}</td>
					<td>${entry.surname}</td>
					<td>${asientos[i.index].status}</td>
					<td>${asientos[i.index].comment}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
</div>
<div class="w3-container w3-quarter">
<h2>Peticiones</h2>
	<table class="w3-table w3-bordered w3-striped w3-card-4">
	<thead>
	<tr>
		<th>usuario</th>
		<th>Acciones</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="entry" items="${peticiones}" varStatus="i">
		<tr id="item_${i.index}">
			<td>${entry.userId}</td>
			<td>
				<a id="aceparPasajero" href="aceparPasajero?idViaje=${param.id}&idUsuario=${entry.userId}" class="w3-btn w3-green w3-large w3-padding">Aceptar pasajero</a>
				<a id="rechazarPasajero" href="rechazarPasajero?idViaje=${param.id}&idUsuario=${entry.userId}" class="w3-btn w3-red w3-large w3-padding">Rechazar pasajero</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</div>