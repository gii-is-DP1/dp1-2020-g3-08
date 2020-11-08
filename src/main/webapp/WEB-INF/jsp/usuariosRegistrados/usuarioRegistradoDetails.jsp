<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuariosRegistrados">

    <h2>User Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${usuarioRegistrado.firstName} ${usuarioRegistrado.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${usuarioRegistrado.correoElectronico}"/></td>
        </tr>
        <tr>
            <th>Birth Day</th>
            <td><c:out value="${usuarioRegistrado.fechaDeNacimiento}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${usuarioRegistrado.telephone}"/></td>
        </tr>
        <tr>
            <th>Genre</th>
            <td><c:out value="${usuarioRegistrado.sexo}"/></td>
        </tr>
    </table>

    <spring:url value="{usuarioRegistradoID}/edit" var="editUrl">
        <spring:param name="usuarioRegistradoID" value="${usuarioRegistrado.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Usuario</a>

</petclinic:layout>
