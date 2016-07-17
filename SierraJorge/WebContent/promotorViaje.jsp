<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="jsp/comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip :: Registrar nuevo viaje</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-container w3-blue">
		<h1>Registrar nuevo viaje :: ShareMyTrip</h1>
		<a href='${pageContext.request.contextPath}'
			class="w3-btn w3-blue w3-btn-block"> <i class="fa fa-home fa-fw"></i>
			Ir a Inicio
		</a>
	</div>
	<div class="w3-row">
		<form id='f' action="verificarCrearViaje" method="post">
			<div class="w3-container w3-third">
				<h2>Origen</h2>
				<p>
					<label for="direccionOrigen">Dirección</label>
					<c:if test="${param.direccionOrigen == null}">
						<input id="direccionOrigen" name="direccionOrigen"
							class="w3-input" type="text" required />
					</c:if>
					<c:if test="${param.direccionOrigen != null}">
						<input id="direccionOrigen" name="direccionOrigen"
							class="w3-input" type="text" required
							value="${param.direccionOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="ciudadOrigen">Ciudad</label>
					<c:if test="${param.ciudadOrigen == null}">
						<input id="ciudadOrigen" name="ciudadOrigen" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.ciudadOrigen != null}">
						<input id="ciudadOrigen" name="ciudadOrigen" class="w3-input"
							type="text" required value="${param.ciudadOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="estadoOrigen">Estado</label>
					<c:if test="${param.estadoOrigen == null}">
						<input id="estadoOrigen" name="estadoOrigen" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.estadoOrigen != null}">
						<input id="estadoOrigen" name="estadoOrigen" class="w3-input"
							type="text" required value="${param.estadoOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="paisOrigen">Pais</label>
					<c:if test="${param.paisOrigen == null}">
						<input id="paisOrigen" name="paisOrigen" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.paisOrigen != null}">
						<input id="paisOrigen" name="paisOrigen" class="w3-input"
							type="text" required value="${param.paisOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="zipOrigen">Código postal</label>
					<c:if test="${param.zipOrigen == null}">
						<input id="zipOrigen" name="zipOrigen" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.zipOrigen != null}">
						<input id="zipOrigen" name="zipOrigen" class="w3-input"
							type="text" required value="${param.zipOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="latOrigen">Latitud</label>
					<c:if test="${param.latOrigen == null}">
						<input id="latOrigen" name="latOrigen" class="w3-input"
							type="number" step="0.001" />
					</c:if>
					<c:if test="${param.latOrigen != null}">
						<input id="latOrigen" name="latOrigen" class="w3-input"
							type="number" step="0.001" value="${param.latOrigen}" />
					</c:if>
				</p>
				<p>
					<label for="lonOrigen">Longitud</label>
					<c:if test="${param.lonOrigen == null}">
						<input id="lonOrigen" name="lonOrigen" class="w3-input"
							type="number" step="0.001" />
					</c:if>
					<c:if test="${param.lonOrigen != null}">
						<input id="lonOrigen" name="lonOrigen" class="w3-input"
							type="number" step="0.001" value="${param.lonOrigen}" />
					</c:if>
				</p>
			</div>
			<div class="w3-container w3-third">
				<h2>Destino</h2>
				<p>
					<label for="direccionDestino">Dirección</label>
					<c:if test="${param.direccionDestino == null}">
						<input id="direccionDestino" name="direccionDestino"
							class="w3-input" type="text" required />
					</c:if>
					<c:if test="${param.direccionDestino != null}">
						<input id="direccionDestino" name="direccionDestino"
							class="w3-input" type="text" required
							value="${param.direccionDestino}" />
					</c:if>
				</p>
				<p>
					<label for="ciudadDestino">Ciudad</label>
					<c:if test="${param.ciudadDestino == null}">
						<input id="ciudadDestino" name="ciudadDestino" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.ciudadDestino != null}">
						<input id="ciudadDestino" name="ciudadDestino" class="w3-input"
							type="text" required value="${param.ciudadDestino}" />
					</c:if>
				</p>
				<p>
					<label for="estadoDestino">Estado</label>
					<c:if test="${param.estadoDestino == null}">
						<input id="estadoDestino" name="estadoDestino" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.estadoDestino != null}">
						<input id="estadoDestino" name="estadoDestino" class="w3-input"
							type="text" required value="${param.estadoDestino}" />
					</c:if>
				</p>
				<p>
					<label for="paisDestino">Pais</label>
					<c:if test="${param.paisDestino == null}">
						<input id="paisDestino" name="paisDestino" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.paisDestino != null}">
						<input id="paisDestino" name="paisDestino" class="w3-input"
							type="text" required value="${param.paisDestino}" />
					</c:if>
				</p>
				<p>
					<label for="zipDestino">Código postal</label>
					<c:if test="${param.zipDestino == null}">
						<input id="zipDestino" name="zipDestino" class="w3-input"
							type="text" required />
					</c:if>
					<c:if test="${param.zipDestino != null}">
						<input id="zipDestino" name="zipDestino" class="w3-input"
							type="text" required value="${param.zipDestino}" />
					</c:if>
				</p>
				<p>
					<label for="latDestino">Latitud</label>
					<c:if test="${param.latDestino == null}">
						<input id="latDestino" name="latDestino" class="w3-input"
							type="number" step="0.001" />
					</c:if>
					<c:if test="${param.latDestino != null}">
						<input id="latDestino" name="latDestino" class="w3-input"
							type="number" step="0.001" value="${param.latDestino}" />
					</c:if>
				</p>
				<p>
					<label for="lonDestino">Longitud</label>
					<c:if test="${param.lonDestino == null}">
						<input id="lonDestino" name="lonDestino" class="w3-input"
							type="number" step="0.001" />
					</c:if>
					<c:if test="${param.lonDestino != null}">
						<input id="lonDestino" name="lonDestino" class="w3-input"
							type="number" step="0.001" value="${param.lonDestino}" />
					</c:if>
				</p>
			</div>
			<div class="w3-container w3-third">
				<h2>Datos del viaje</h2>
				<p>
					<label for="salida">Fecha de salida</label>
					<c:if test="${param.salida == null}">
						<input id="salida" name="salida" class="w3-input" type="date"
							required />
					</c:if>
					<c:if test="${param.salida != null}">
						<input id="salida" name="salida" class="w3-input" type="date"
							required value="${param.salida}" />
					</c:if>
				</p>
				<p>
					<label for="salidaH">Hora de salida</label>
					<c:if test="${param.salidaH == null}">
						<input id="salidaH" name="salidaH" class="w3-input" type="time"
							required />
					</c:if>
					<c:if test="${param.salida != null}">
						<input id="salidaH" name="salidaH" class="w3-input" type="time"
							required value="${param.salidaH}" />
					</c:if>
				</p>
				<p>
					<label for="llegada">Fecha de llegada</label>
					<c:if test="${param.llegada == null}">
						<input id="llegada" name="llegada" class="w3-input" type="date"
							required />
					</c:if>
					<c:if test="${param.llegada != null}">
						<input id="llegada" name="llegada" class="w3-input" type="date"
							required value="${param.llegada}" />
					</c:if>
				</p>
				<p>
					<label for="llegadaH">Hora de llegada</label>
					<c:if test="${param.llegadaH == null}">
						<input id="llegadaH" name="llegadaH" class="w3-input" type="time"
							required />
					</c:if>
					<c:if test="${param.llegadaH != null}">
						<input id="llegadaH" name="llegadaH" class="w3-input" type="time"
							required value="${param.llegadaH}" />
					</c:if>
				</p>
				<p>
					<label for="cierre">Fecha de cierre de peticiones</label>
					<c:if test="${param.cierre == null}">
						<input id="cierre" name="cierre" class="w3-input" type="date"
							required />
					</c:if>
					<c:if test="${param.llegada != null}">
						<input id="cierre" name="cierre" class="w3-input" type="date"
							required value="${param.cierre}" />
					</c:if>
				</p>
				<p>
					<label for="plazas">Plazas disponibles</label>
					<c:if test="${param.plazas == null}">
						<input id="plazas" name="plazas" class="w3-input" type="number"
							min="1" required />
					</c:if>
					<c:if test="${param.plazas != null}">
						<input id="plazas" name="plazas" class="w3-input" type="number"
							min="1" required value="${param.plazas}" />
					</c:if>
				</p>
				<p>
					<label for="coste">Coste estimado</label>
					<c:if test="${param.coste == null}">
						<input id="coste" name="coste" class="w3-input" type="number"
							step="0.01" min="0" required />
					</c:if>
					<c:if test="${param.coste != null}">
						<input id="coste" name="coste" class="w3-input" type="number"
							step="0.01" min="0" required value="${param.coste}" />
					</c:if>
				</p>
			</div>
			<div class="w3-container">
				<p>
					<label for="comentario">Comentario</label>
					<c:if test="${param.comentario == null}">
						<input id="comentario" name="comentario" class="w3-input"
							type="text" />
					</c:if>
					<c:if test="${param.comentario != null}">
						<input id="comentario" name="comentario" class="w3-input"
							type="text" value="${param.comentario}" />
					</c:if>
				</p>
			</div>
			<input id='idPromotor' name='idPromotor' type="hidden" value="${user.id}"/>
			<input type="submit" value="Confirmar" id='vv' name="vv"
			class="w3-btn w3-blue w3-btn-block" />
		</form>
	</div>
	<div class="w3-container">
		
		
		<div class="w3-card-4 w3-yellow">
  		<p>En caso de que no se active el complemento para introducir la fecha y hora siga el siguiente formato:</p>
		<pre>
                 Formato           Ejemplo
        Fechas  AAAA-MM-DD        2016-05-16
        Horas   hh:mm             10:35
		</pre>
		</div>			
					
		<jsp:include page="jsp/message.jsp"></jsp:include>
		
		<hr>
		</div>
		<a href='${pageContext.request.contextPath}'
			class="w3-btn w3-blue w3-btn-block"><i class="fa fa-home fa-fw"></i>
			Ir a Inicio</a>
	
</body>
</html>