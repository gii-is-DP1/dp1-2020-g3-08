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

</petclinic:layout>