
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="arbitro">
	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fechaNacimiento").datepicker({
											dateFormat : 'yy/mm/dd'
										});
									});
								</script>
    </jsp:attribute>
	<jsp:body>
        <h2>
            <c:if test="${arbitro['new']}">Nuevo </c:if> &Aacute;rbitro
        </h2>
        <form:form modelAttribute="arbitro" class="form-horizontal">
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
                        <c:when test="${arbitro['new']}">
                            <button class="btn btn-default" type="submit">A&ntilde;adir &Aacute;rbitro</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Actualizar &Aacute;rbitro</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>
        <c:if test="${!arbitro['new']}">
        </c:if>
    </jsp:body>
</petclinic:layout>
