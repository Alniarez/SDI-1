<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Mensajes -->
<c:if test="${requestScope.errorMessage != null}">
	<div class="w3-container w3-section w3-red">
		<span onclick="this.parentElement.style.display='none'"
			class="w3-closebtn">X</span>
		<h3>¡Se ha producido un error!</h3>
		<p id='errorMessage'>${requestScope.errorMessage}</p>
	</div>
</c:if>
<c:if test="${requestScope.warningMessage != null}">
	<div class="w3-container w3-section w3-yellow">
		<span onclick="this.parentElement.style.display='none'"
			class="w3-closebtn">X</span>
		<h3>¡Precaución!</h3>
		<p id='warningMessage'>${requestScope.warningMessage}</p>
	</div>
</c:if>
<c:if test="${requestScope.successMessage != null}">
	<div class="w3-container w3-section w3-green">
		<span onclick="this.parentElement.style.display='none'"
			class="w3-closebtn">X</span>
		<h3>¡Éxito!</h3>
		<p id='successMessage'>${requestScope.successMessage}</p>
	</div>
</c:if>