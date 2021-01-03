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
            <td><c:out value="${noticia.fecha}"/></td>
        </tr>
        <tr>
            <th>Titular</th>
            <td><b><c:out value="${noticia.titulo}"/></b></td>
        </tr>
        <tr>
            <th>Texto</th>
            <td><c:out value="${noticia.texto}"/></td>
        </tr>
    </table>
    <h3>Partidos Relacionados</h3>
    <table class="table table-striped">
		<c:forEach var="partido" items="${noticia.partidos}">
			<tr>
				<td valign="top">
					<dl class="dl-horizontal">
						<dt>Lugar</dt>
						<dd>
							<c:out value="${partido.lugar}" />
						</dd>
						<dt>Fecha</dt>
						<dd>
							<c:out value="${partido.fecha}" />
						</dd>
						<spring:url value="/partidos/{partidoId}" var="partidoUrl">
							<spring:param name="partidoId" value="${partido.id}" />
						</spring:url>
						<a href="${fn:escapeXml(partidoUrl)}">Ver Partido</a>
					</dl>
				</td>
			</tr>
		</c:forEach>
	</table>

</petclinic:layout>
