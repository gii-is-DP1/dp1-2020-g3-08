<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="users">

    <h2>User Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${user.firstName} ${user.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><c:out value="${user.email}"/></td>
        </tr>
        <tr>
            <th>Birth Date</th>
            <td><c:out value="${user.birthDate}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${user.telephone}"/></td>
        </tr>
        <tr>
            <th>Genre</th>
            <td><c:out value="${user.genre}"/></td>
        </tr>
    </table>

    <spring:url value="{username}/edit" var="editUrl">
        <spring:param name="username" value="${user.username}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit User</a>

</petclinic:layout>
