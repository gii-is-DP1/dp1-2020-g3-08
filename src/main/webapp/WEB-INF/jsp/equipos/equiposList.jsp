<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="equipos">
	<h2>Equipos</h2>

	<table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Lugar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="equipo">
            <tr>
                <td>
                    <spring:url value="/equipos/{equipoId}" var="equipoUrl">
                        <spring:param name="equipoId" value="${equipo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(equipoUrl)}"><c:out value="${equipo.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${equipo.lugar}"/>
                </td>
                     
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
