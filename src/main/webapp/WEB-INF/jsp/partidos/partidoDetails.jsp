<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

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

</petclinic:layout>
