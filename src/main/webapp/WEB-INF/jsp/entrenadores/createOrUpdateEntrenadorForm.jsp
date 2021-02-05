<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.petclinic.model.Genre" %>

<petclinic:layout pageName="entrenadores">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("&user.birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
	    <h2>
	        <c:if test="${entrenador['new']}">Nuevo </c:if>
	        <c:if test="${!entrenador['new']}">Editar </c:if> entrenador
	    </h2>
	    <form:form modelAttribute="entrenador" class="form-horizontal" id="add-entrenador-form">
	        <div class="form-group has-feedback">
	            <petclinic:inputField label="First Name" name="user.firstName"/>
	            <petclinic:inputField label="Last Name" name="user.lastName"/>
	            <petclinic:inputField label="Email" name="user.email"/>
	            <petclinic:inputField label="BirthDate" name="user.birthDate"/>
	            <div class="control-group">	
	            	<petclinic:selectField label="Genre" name="user.genre" size="2" names="${genres}"/>
	            </div>
	            <petclinic:inputField label="Telephone" name="user.telephone"/>
	            <petclinic:inputField label="Username" name="user.username"/>
	            <petclinic:inputField label="Password" name="user.password"/>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">
	                <c:choose>
	                    <c:when test="${entrenador['new']}">
	                        <button class="btn btn-default" type="submit">Registrar</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class="btn btn-default" type="submit">Editar</button>
	                    </c:otherwise>
	                </c:choose>
	            </div>
	        </div>
	    </form:form>
	</jsp:body>
</petclinic:layout>
