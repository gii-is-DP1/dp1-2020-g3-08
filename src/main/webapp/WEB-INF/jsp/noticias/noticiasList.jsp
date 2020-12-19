<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="noticias">
    <h2>noticias</h2>

    <table id="noticiasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Titulo</th>
            <th style="width: 130px;">Fecha</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${noticias}" var="noticia">
            <tr>
                <td>
                    <spring:url value="/noticias/{noticiasId}" var="noticiaUrl">
                        <spring:param name="noticiasId" value="${noticia.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(noticiaUrl)}"><c:out value="${noticia.title}"/></a>
                </td>
                <td>
                    <c:out value="${noticia.date}"/>
                </td>           
            </tr>
        </c:forEach>            
        </tbody>
    </table>
    <a href="/noticias/new" class="btn btn-default">Nueva Noticia</a>
</petclinic:layout>
