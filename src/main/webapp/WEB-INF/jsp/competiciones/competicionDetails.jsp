<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="competiciones">

    <h2>Competicion Information</h2>


    <table class="table table-striped">
    	<tr>
            <th>Nombre de la competicion</th>
            <td><c:out value="${competicion.nombreComp}"/></td>
        </tr>
        
    </table>

</petclinic:layout>