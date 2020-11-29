<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 130px;">Apellidos</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="jugador">
            <tr>
                <td>
                    <spring:url value="/jugadores/{jugadorId}" var="jugadorUrl">
                        <spring:param name="jugadorId" value="${equipo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(jugadorUrl)}"><c:out value="${jugador.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${jugador.apellidos}"/>
                </td>           
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
