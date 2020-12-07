<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="equipo">
	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fecha_nacimiento").datepicker({
											dateFormat : 'yy/mm/dd'
										});
									});
								</script>
    </jsp:attribute>
	<jsp:body>
        <h2>
            <c:if test="${jugador['new']}">New </c:if> Jugador
        </h2>
        <form:form modelAttribute="jugador" class="form-horizontal">
                <div class="form-group has-feedback">
                <petclinic:inputField label="Nombre" name="nombre" />
                <petclinic:inputField label="Apellidos" name="apellidos" />
                <petclinic:inputField label="Fecha Nacimiento" name="fechaNacimiento" />
                <petclinic:inputField label="Nacionalidad" name="nacionalidad" />
                <petclinic:inputField label="DNI" name="dni" />
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${jugador['new']}">
                            <button class="btn btn-default" type="submit">Añadir Jugador</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Actualizar Jugador</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!jugador['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
