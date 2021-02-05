<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="jugadores">
	<h2>Jugadores</h2>

	<table id="ownersTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 200px;">Nombre</th>
				<th style="width: 200px;">Apellidos</th>
				<th style="width: 200px;">Equipo</th>
				<th style="width: 200px;">Amarillas</th>
				<th style="width: 200px;">Rojas</th>
				<th style="width: 200px;">Lesi&oacute;n</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${selections}" var="jugador">
				<tr>
					<td><c:out value="${jugador.nombre}" /></td>
					<td><c:out value="${jugador.apellidos}" /></td>
					<td><c:out value="${jugador.equipo.nombre}" /></td>
					<td><c:out value="${jugador.tarjetaAmarilla}" /></td>
					<td><c:out value="${jugador.tarjetaRoja}" /></td>
					<c:choose>
                        <c:when test="${jugador['lesion']}">
                            <td><c:out value="Sí" /></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:out value="No" /></td>
                        </c:otherwise>
                    </c:choose>			
					


					<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->

				</tr>
			</c:forEach>
		</tbody>
	</table>
</petclinic:layout>
