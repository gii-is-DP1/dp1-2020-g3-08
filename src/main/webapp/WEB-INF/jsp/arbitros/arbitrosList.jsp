<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">
    <h2>Arbitros</h2>

    <table id="arbitrosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${arbitros.arbitroList}" var="arbitro">
            <tr>
                <td>
                 <spring:url value="/arbitros/{arbitroId}" var="arbitroUrl">
                        <spring:param name="arbitroId" value="${arbitro.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(arbitroUrl)}"><c:out value="${arbitro.nombre} ${arbitro.apellidos}"/></a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/arbitros.xml" htmlEscape="true" />">View as XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout>