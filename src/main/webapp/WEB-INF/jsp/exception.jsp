<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/jugador_imagen.png" var="jugadorImage"/>
    <img src="${jugadorImage}"/>

    <h2>No se puede registrar</h2>

    <p>${exception.message}</p>
    
	
	
</petclinic:layout>
