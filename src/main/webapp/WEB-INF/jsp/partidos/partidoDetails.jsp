<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidos">

    <h2>Informaci&oacute;n Partido</h2>


    <table class="table table-striped">
        <tr>
            <th>Fecha</th>
            <td><b><c:out value="${partido.fecha}"/></b></td>
        </tr>
        <tr>
            <th>Lugar</th>
            <td><c:out value="${partido.lugar}"/></td>
        </tr>
        <tr>
            <th>Equipo Visitante</th>
            <td><c:out value="${partido.equipo1.nombre}"/></td>
        </tr>
        <tr>
            <th>Equipo Casa</th>
            <td><c:out value="${partido.equipo2.nombre}"/></td>
        </tr>
        <tr>
            <th>&Aacute;rbitro</th>
            <td><c:out value="${partido.arbitro.nombre}"/></td>
        </tr>

    </table>
    <spring:url value="{id}/administrarJugadores" var="adminUrl">
        <spring:param name="id" value="${partido.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(adminUrl)}" class="btn btn-default">Admin Jugadores</a>
    
     <h2>Jugadores del equipo visitante</h2>
    <table class="table table-striped">
		<c:forEach var="jugador" items="${partido.equipo1.jugadores}">
		
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
						
					</dl>
				</td>
			</tr>
		</c:forEach>
	</table>

	<h2>Jugadores del equipo local</h2>
    <table class="table table-striped">
		<c:forEach var="jugador" items="${partido.equipo2.jugadores}">
		
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
						
					</dl>
				</td>
			</tr>
		</c:forEach>
	</table>
    
    
    


</petclinic:layout>
