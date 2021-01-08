<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="arbitros">

    <h2>Arbitro Information</h2>


    <table class="table table-striped">
    	 <tr>
            <th>Nombre</th>
            <td><b><c:out value="${arbitro.nombre} ${arbitro.apellidos}"/></b></td>
        </tr>
        
        <tr>
            <th>Fecha de nacimiento</th>
            <td><c:out value="${arbitro.fecha_nacimiento}"/></td>
        </tr>
        <tr>
            <th>Nacionalidad</th>
            <td><c:out value="${arbitro.nacionalidad}"/></td>
        </tr>
        <tr>
            <th>Dni</th>
            <td><c:out value="${arbitro.dni}"/></td>
        </tr>
    </table>
    <spring:url value="{arbitroId}/delete" var="deleteUrl">
		<spring:param name="arbitroId" value="${arbitro.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Borrar Arbitro</a>

</petclinic:layout>
