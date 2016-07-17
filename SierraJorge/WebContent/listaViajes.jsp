<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="jsp/comprobarNavegacion.jsp"%>

<!DOCTYPE html>
<html>

<head>
<title>ShareMyTrip :: Próximos viajes</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>

<body>
	<div class="w3-container w3-blue">
		<h1>Próximos viajes :: ShareMyTrip</h1>
		<a href='${pageContext.request.contextPath}' class="w3-btn w3-blue w3-btn-block">
			<i class="fa fa-home fa-fw"></i> Ir a Inicio
		</a>
	</div>


	<div class="w3-container w3-padding-32">
		<table id='dataTable'
			class="w3-table w3-bordered w3-striped w3-border">
			<thead>
				<tr>
					<th>ID viaje</th>
					<th>Origen</th>
					<th>Destino</th>
					<th>Plazas libres</th>
					<th>Datos del viaje</th>
					<c:if test="${user!=null}">
						<th>Sobre el viaje</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${listaViajes}" varStatus="i">
					<tr id="item_${i.index}">
						<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
						<td>${entry.departure.city}</td>
						<td>${entry.destination.city}</td>
						<td>${entry.availablePax}</td>
						<td><a id="${entry.id}" href="mostrarViaje?id=${entry.id}" class="w3-btn w3-ripple w3-red"><i class="fa fa-search"></i> Ver información</a></td>
						<c:if test="${user.id == entry.promoterId}">
							<td><span class="w3-tag w3-round w3-dark-grey">Mi viaje</span></td>
						</c:if>
						<c:if test="${user.id != entry.promoterId}">
							<td></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<a href='${pageContext.request.contextPath}' class="w3-btn w3-blue w3-btn-block"><i class="fa fa-home fa-fw"></i>Ir a Inicio</a>
<script src="js/jquery-2.2.1.js"></script>
<script src="js/datatables.js"></script>
<script>
	$(document).ready(function() {
		$('#dataTable')
			.DataTable(
			{
				"language" : {
					"sProcessing" : "Procesando...",
					"sLengthMenu" : "Mostrar _MENU_ registros",
					"sZeroRecords" : "No se encontraron resultados",
					"sEmptyTable" : "Ningún dato disponible en esta tabla",
					"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
					"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
					"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
					"sInfoPostFix" : "",
					"sSearch" : "Buscar:",
					"sUrl" : "",
					"sInfoThousands" : ",",
					"sLoadingRecords" : "Cargando...",
					"oPaginate" : {
						"sFirst" : "Primero",
						"sLast" : "Último",
						"sNext" : "Siguiente",
						"sPrevious" : "Anterior"
					},
					"oAria" : {
						"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
						"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
					}
			}
		});
	});
</script>
</body>
</html>


<!-- 
<div class="w3-accordion w3-light-grey">
	<button id='mostrarFiltro'
		class="w3-btn-block w3-left-align w3-light-blue">Filtrar</button>
	<div id="filtro" class="w3-accordion-content">
		<div class="w3-row">
			<div class="w3-container w3-half">
				<form id="formFiltro">
					<p>
						<label for="ciudadOrigen">Origen</label> <input id="ciudadOrigen"
							name="ciudadOrigen" class="w3-input" type="text" />
					</p>
					<p>
						<label for="ciudadDestino">Destino</label> <input
							id="ciudadDestino" name="ciudadDestino" class="w3-input"
							type="text" />
					</p>
				</form>
			</div>
			<div class="w3-container w3-half">
			</div>
		</div>
	</div>
</div>
<script >
	$("#mostrarFiltro").click(function() {
			$("#filtro").toggle();
		});
</script>
 -->