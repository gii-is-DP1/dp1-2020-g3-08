<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
<h2>Competiciones</h2>

	<table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de la competicion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${competiciones.competicionList}" var="competicion">
            <tr>
                <td>
                    <spring:url value="/competiciones/{competicionId}" var="competicionUrl">
                        <spring:param name="competicionId" value="${competicion.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(competicionUrl)}"><c:out value="${competicion.nombreComp}"/></a>
                </td>
      
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
