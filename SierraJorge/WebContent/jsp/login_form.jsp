<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Si ya está logueado va a inicio. No se puede poner /jsp/login_form.jsp directamente. -->
<c:if test="${user != null}">
	<jsp:forward page="${pageContext.request.contextPath}"></jsp:forward>
</c:if>

<!-- Mostrar botones -->
<p>Inicie sesión para acceder a todos los servicios.</p>

<button id="bl" onclick="document.getElementById('loginForm').style.display='block'" class="w3-btn w3-blue w3-large w3-padding"><i class="fa fa-user"></i> Identificarse</button>
<button id="br" onclick="document.getElementById('registerForm').style.display='block'" class="w3-btn w3-blue w3-large w3-padding"><i class="fa fa-user-plus"></i> Crear cuenta</button>

<!-- Modal de identificación -->
<div id="loginForm" class="w3-modal">
	<div class="w3-modal-content">
		<div class="w3-container w3-padding-32">
			<div class="w3-container w3-blue">
				<span onclick="document.getElementById('loginForm').style.display='none'" class="w3-closebtn">×</span>
  				<h1>Identificarse</h1>
			</div>
			<form action="validarse" method="post" class="w3-container">
				<p>
					<label for="nombreUsuarioLogin">Usuario</label>
					<input id="nombreUsuarioLogin" name="nombreUsuarioLogin" class="w3-input" type="text" required/>
				</p>
				<p>
					<label for="claveUsuarioLogin">Clave</label>
					<input id="claveUsuarioLogin" name="claveUsuarioLogin" class="w3-input" type=password required/>
				</p>
				<input name="loginBut" id="loginBut" type="submit" value="Identificarse" class="w3-btn w3-blue" />
			</form>
		</div>
	</div>
</div>

<!-- Modal de registro -->
<div id="registerForm" class="w3-modal">
	<div class="w3-modal-content">
		<div class="w3-container w3-padding-32">
			<div class="w3-container w3-blue">
				<span onclick="document.getElementById('registerForm').style.display='none'" class="w3-closebtn">×</span>
  				<h1>Crear cuenta</h1>
			</div>
			<form action="crearCuenta" method="post" class="w3-container">
				<p>
					<label for="nombreUsuarioRegister">Nombre de usuario</label>
					<input id="nombreUsuarioRegister" name="nombreUsuarioRegister" class="w3-input" type="text" required/>
				</p>
				<p>
					<label for="nombreRegister">Nombre</label>
					<input id="nombreRegister" name="nombreRegister" class="w3-input" type="text" required/>
				</p>
				<p>
					<label for="apellidoRegister">Apellidos</label>
					<input id="apellidoRegister" name="apellidoRegister" class="w3-input" type="text" required/>
				</p>
				<p>
					<label for="emailRegister">E-mail</label>
					<input id="emailRegister" name="emailRegister" class="w3-input" type="email" required/>
				</p>
				<p>
					<label for="claveRegister1">Clave</label>
					<input id="claveRegister1" name="claveRegister1" class="w3-input" type=password required />
				</p>
				<p>
					<label for="claveRegister2">Repita la clave</label>
					<input id="claveRegister2" name="claveRegister2" class="w3-input" type=password required />
				</p>
				<input name='registernBut' id="registernBut" type="submit" value="Crear cuenta" class="w3-btn w3-blue" />
			</form>
		</div>
	</div>
</div>
