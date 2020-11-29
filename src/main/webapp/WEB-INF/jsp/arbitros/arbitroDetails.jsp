<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="arbitros">

    <h2>Arbitro Information</h2>


    <table class="table table-striped">
    	<tr>
            <th>Nombre</th>
            <td><c:out value="${arbitro.nombreArbitro}"/></td>
        </tr>
       
    </table>

</petclinic:layout>
