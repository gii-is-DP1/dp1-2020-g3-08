<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<petclinic:layout pageName="partidos">

    <h2>Partido Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Fecha</th>
            <td><b><c:out value="${partido.fecha}"/></b></td>
        </tr>
        <tr>
            <th>Lugar</th>
            <td><c:out value="${partido.lugar}"/></td>
        </tr>
    </table>
    <spring:url value="{partidoId}/arbitros/new" var="addUrl">
		<spring:param name="partidoId" value="${partido.id}" />
	</spring:url>
	<jstl:if test="${partido.arbitro.nombre != null}">
				<a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Añadir nuevo arbitro</a>
	    </jstl:if>	
	

	<br />
	<br />
	<br />
    

</petclinic:layout>
