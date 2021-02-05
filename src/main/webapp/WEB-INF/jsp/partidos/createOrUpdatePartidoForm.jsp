<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidos">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fecha").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${partido['new']}">Nuevo </c:if> Partido
    </h2>
    <form:form modelAttribute="partido" class="form-horizontal" id="add-partido-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Lugar" name="lugar"/>
            <petclinic:inputField label="fecha" name="fecha"/>
            <div class="control-group">	
	            	<petclinic:selectField label="Equipo1" name="equipo1" size="5" names="${equipos}"/>
	        </div>
	        <div class="control-group">	
	            	<petclinic:selectField label="Equipo2" name="equipo2" size="5" names="${equipos}"/>
	        </div>
	        <div class="control-group">	
	            	<petclinic:selectField label="Arbitro" name="arbitro" size="5" names="${arbitros}"/>
	        </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">A&ntilde;adir Partido</button>
            </div>
        </div>
    </form:form>
    </jsp:body>
 
</petclinic:layout>