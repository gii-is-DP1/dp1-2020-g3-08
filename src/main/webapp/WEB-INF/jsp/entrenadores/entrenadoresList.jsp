<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="entrenadores">
<h2>Entrenadores</h2>

	<table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 200px;">Apellidos</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${entrenadores}" var="entrenador">
            <tr>
                <td>
                    <spring:url value="/entrenadores/{entrenadorId}" var="entrenadorUrl">
                        <spring:param name="entrenadorId" value="${entrenador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(entrenadorUrl)}"><c:out value="${entrenador.user.firstName}"/></a>
                </td>
                <td>
                    <c:out value="${entrenador.user.lastName}"/>
                </td>
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
