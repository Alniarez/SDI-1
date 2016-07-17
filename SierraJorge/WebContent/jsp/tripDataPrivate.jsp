<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<hr>
<div class="w3-row">
<!-- El usuario es promotor -->
	<c:if test="${user.id == trip.promoterId}">
		<jsp:include page="tripDataPrivatePro.jsp"></jsp:include>
	</c:if>
<!-- El usuario no es promotor -->
	<c:if test="${user.id != trip.promoterId}">
		<jsp:include page="tripDataPrivatePas.jsp"></jsp:include>
	</c:if>
</div>