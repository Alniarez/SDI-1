<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="w3-container w3-quarter">
	<h2>Datos del promotor</h2>
	<h3>Nombre</h3>
	<p>${requestScope.pro.name}${requestScope.pro.surname}</p>
	<h3>E-mail</h3>
	<p>${requestScope.pro.email}</p>
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
	<h2>Pedir plaza</h2>
	<a id="solicitarPlaza"
		href="solicitarPlaza?idViaje=${param.id}&idUsuario=${ user.id }"
		class="w3-btn w3-red w3-large w3-padding">¡Me apunto!</a>
</div>

