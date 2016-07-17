<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="jsp/comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip :: Perfil</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />

	<header class="w3-container w3-blue">
		<h1>Perfil :: ShareMyTrip</h1>
		<a href='${pageContext.request.contextPath}'
			class="w3-btn w3-blue w3-btn-block"><i class="fa fa-home fa-fw"></i>Ir
			a Inicio</a>
	</header>
	<div class="w3-content" style="max-width: 600px">
		<div class="w3-container w3-card-2 w3-indigo">
			<div class="w3-row">
				<div class="w3-container w3-half">
					<h1><jsp:getProperty property="login" name="user" /></h1>
				</div>
				<div class="w3-container w3-half">
					<h5>
						<button
							onclick="document.getElementById('modPerfil').style.display='block'"
							class="w3-btn w3-red">
							<i class="fa fa-gear"></i> Modificar perfil
						</button>
					</h5>
				</div>
			</div>


		</div>
		<ul class="w3-ul">
			<li>
				<h3>Nombre de usuario</h3>
				<p><jsp:getProperty property="login" name="user" /></p>
			</li>
			<li>
				<h3>Nombre</h3>
				<p><jsp:getProperty property="name" name="user" /></p>
			</li>
			<li>
				<h3>Apellidos</h3>
				<p><jsp:getProperty property="surname" name="user" /></p>
			</li>
			<li>
				<h3>Email</h3>
				<p><jsp:getProperty property="email" name="user" /></p>
			</li>
		</ul>
	</div>





	<!-- Modal de modificacion -->
	<div id="modPerfil" class="w3-modal">
		<div class="w3-modal-content">
			<div class="w3-container w3-padding-32">
				<div class="w3-container w3-blue">
					<span
						onclick="document.getElementById('modPerfil').style.display='none'"
						class="w3-closebtn">×</span>
					<h1>Modificar perfil</h1>
				</div>

				<p>Escriba los datos que desee modificar.</p>

				<form action="modificarPerfil" method="post" class="w3-container">
					<p>
						<label for="nombreMod">Nombre</label> <input id="nombreMod"
							name="nombreMod" class="w3-input" type="text" />
					</p>
					<p>
						<label for="apellidoMod">Apellidos</label> <input id="apellidoMod"
							name="apellidoMod" class="w3-input" type="text" />
					</p>
					<p>
						<label for="emailMod">E-mail</label> <input id="emailMod"
							name="emailMod" class="w3-input" type="text" />
					</p>
					<p>
						<label for="claveMod1">Clave nueva</label> <input id="claveMod1"
							name="claveMod1" class="w3-input" type="password" />
					</p>
					<p>
						<label for="claveMod2">Repita la clave</label> <input
							id="claveMod2" name="claveMod2" class="w3-input" type="password" />
					</p>
					<p>
						<label for="claveActual">Escriba la clave actual</label> <input
							id="claveActual" name="claveActual" class="w3-input"
							type="password" />
					</p>
					<input type="hidden" name='login'
						value='<jsp:getProperty property="login" name="user" />' /> <input
						type="submit" value="Modificar Perfil" class="w3-btn w3-blue" />
				</form>
			</div>
		</div>
	</div>

	<!-- Mensajes -->
	<jsp:include page="jsp/message.jsp"></jsp:include>

	<div class="w3-container">
		<h2>Mis viajes</h2>
		<div class="w3-container w3-padding-32">
			<table id='dataTable'
				class="w3-table w3-bordered w3-striped w3-border">
				<thead>
					<tr>
						<th>ID viaje</th>
						<th>Origen</th>
						<th>Destino</th>
						<th>Datos del viaje</th>
						<th>Sobre el viaje</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="entry" items="${listaViajes}" varStatus="i">
						<tr id="item_${i.index}">
							<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
							<td>${entry.departure.city}</td>
							<td>${entry.destination.city}</td>
							<td><a href="mostrarViaje?id=${entry.id}"
								class="w3-btn w3-ripple w3-red"><i class="fa fa-search"></i>
									Ver información</a></td>
							<c:if test="${user.id == entry.promoterId}">
								<td><span class="w3-tag w3-round w3-dark-grey">Mi viaje</span></td>
							</c:if>
							<c:if test="${user.id != entry.promoterId}">
								<td>Participante</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
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
	<a href='${pageContext.request.contextPath}'
		class="w3-btn w3-blue w3-btn-block"><i class="fa fa-home fa-fw"></i>Ir
		a Inicio</a>
</body>
</html>