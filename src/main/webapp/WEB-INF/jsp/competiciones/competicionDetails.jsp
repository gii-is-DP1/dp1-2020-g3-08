<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="competiciones">

    <h2>Competicion Information</h2>


    <table class="table table-striped">
    	<tr>
            <th>Nombre de la competicion</th>
            <td><c:out value="${competicion.nombreComp}"/></td>
        </tr>
        
    </table>
    
    <spring:url value="{competicionId}/edit" var="editUrl">
		<spring:param name="competicionId" value="${competicion.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Competicion</a>
	
	<spring:url value="{competicionId}/delete" var="deleteUrl">
		<spring:param name="competicionId" value="${competicion.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Borrar Competicion</a>

	
	<spring:url value="{competicionId}/equipos/new" var="addUrl">
		<spring:param name="competicionId" value="${competicion.id}" />
	</spring:url>
	<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Añadir nuevo equipo</a>
	
	<h2>Equipos</h2>

	<table class="table table-striped">
		<c:forEach var="equipo" items="${competicion.equipos}">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Nombre</dt>
						<dd>
						 <spring:url value="/equipos/{equipoId}" var="equipoUrl">
                        <spring:param name="equipoId" value="${equipo.id}"/>
                    </spring:url>
						   <a href="${fn:escapeXml(equipoUrl)}"><c:out value="${equipo.nombre}"/></a>
						</dd>
						<dt>Lugar</dt>
						<dd>
							<c:out value="${equipo.lugar}" />
						</dd>
						<spring:url value="/competiciones/{competicionId}/equipos/{equipoId}/edit" var="equipoUrl">
							<spring:param name="competicionId" value="${competicion.id}" />
							<spring:param name="equipoId" value="${equipo.id}" />
						</spring:url>
						<a href="${fn:escapeXml(equipoUrl)}">Editar equipo</a>
						<spring:url value="/competiciones/{competicionId}/equipos/{equipoId}/delete" var="equipoUrl">
							<spring:param name="competicionId" value="${competicion.id}" />
							<spring:param name="equipoId" value="${equipo.id}" />
						</spring:url>
						<a href="${fn:escapeXml(equipoUrl)}">Borrar equipo</a>
					</dl>
				</td>
			</tr>
		</c:forEach>
	</table>
	


</petclinic:layout>