<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="noticias">

    <h2>Noticia Information</h2>


    <table class="table table-striped">
    	<tr>
            <th>Fecha</th>
            <td><c:out value="${noticia.date}"/></td>
        </tr>
        <tr>
            <th>Titular</th>
            <td><b><c:out value="${noticia.title}"/></b></td>
        </tr>
        <tr>
            <th>Texto</th>
            <td><c:out value="${noticia.text}"/></td>
        </tr>
    </table>

</petclinic:layout>
