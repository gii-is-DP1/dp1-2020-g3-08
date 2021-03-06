<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="equipos">

	<h2>Equipo</h2>


	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<td><b><c:out value="${equipo.nombre}" /></b></td>
		</tr>
		<tr>
			<th>Lugar</th>
			<td><c:out value="${equipo.lugar}" /></td>
		</tr>
		<tr>
			<th>Entrenador</th>
			<td><c:out value="${equipo.entrenador.user.username}" /></td>
		</tr>
	</table>
	
	<spring:url value="{equipoId}/jugadores/new" var="addUrl">
		<spring:param name="equipoId" value="${equipo.id}" />
	</spring:url>

	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">A&ntilde;adir nuevo jugador</a>
	<spring:url value="{equipoId}/entrenadores/new" var="addUrl">
		<spring:param name="equipoId" value="${equipo.id}" />
	</spring:url>
	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">A&ntilde;adir nuevo entrenador</a>
	<h2>Entrenador</h2>

	<table class="table table-striped">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Nombre</dt>
						<dd>
							<c:out value="${equipo.entrenador.user.firstName}" />
						</dd>
						<dt>Apellidos</dt>
						<dd>
							<c:out value="${equipo.entrenador.user.lastName}" />
						</dd>
						<spring:url value="/equipos/{equipoId}/entrenadores/{entrenadorId}/edit" var="entrenadorUrl">
							<spring:param name="equipoId" value="${equipo.id}" />
							<spring:param name="entrenadorId" value="${equipo.entrenador.id}" />
						</spring:url>
						<a href="${fn:escapeXml(entrenadorUrl)}">Editar entrenador</a>
						
					</dl>
				</td>
			</tr>
	</table>
	

	<h2>Jugadores</h2>

	<table class="table table-striped">
		<c:forEach var="jugador" items="${equipo.jugadores}">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Nombre</dt>
						<dd>
							<c:out value="${jugador.nombre}" />
						</dd>
						<dt>Apellidos</dt>
						<dd>
							<c:out value="${jugador.apellidos}" />
						</dd>
						<spring:url value="/equipos/{equipoId}/jugadores/{jugadorId}/edit" var="jugadorUrl">
							<spring:param name="equipoId" value="${equipo.id}" />
							<spring:param name="jugadorId" value="${jugador.id}" />
						</spring:url>
						<a href="${fn:escapeXml(jugadorUrl)}">Editar jugador</a>
						<spring:url value="/equipos/{equipoId}/jugadores/{jugadorId}/delete" var="jugadorUrl">
							<spring:param name="equipoId" value="${equipo.id}" />
							<spring:param name="jugadorId" value="${jugador.id}" />
						</spring:url>
						<a href="${fn:escapeXml(jugadorUrl)}">Borrar jugador</a>
					</dl>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	
</petclinic:layout>
