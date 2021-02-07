<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h2>Informaci&oacute;n Entrenador</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${entrenador.user.firstName} ${entrenador.user.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${entrenador.user.email}"/></td>
        </tr>
        <tr>
            <th>Fecha Nacimiento</th>
            <td><c:out value="${entrenador.user.birthDate}"/></td>
        </tr>
        <tr>
            <th>Tel&eacute;fono</th>
            <td><c:out value="${entrenador.user.telephone}"/></td>
        </tr>
        <tr>
            <th>Sexo</th>
            <td><c:out value="${entrenador.user.genre}"/></td>
        </tr>
    </table>


</petclinic:layout>
