<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.petclinic.model.Genre" %>

<petclinic:layout pageName="users">
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
	    <h2>
	        <c:if test="${user['new']}">New </c:if>
	        <c:if test="${!user['new']}">Edit </c:if> User
	    </h2>
	    <form:form modelAttribute="user" class="form-horizontal" id="add-user-form">
	        <div class="form-group has-feedback">
	            <petclinic:inputField label="First Name" name="firstName"/>
	            <petclinic:inputField label="Last Name" name="lastName"/>
	            <petclinic:inputField label="Email" name="email"/>
	            <petclinic:inputField label="BirthDate" name="birthDate"/>
	            <div class="control-group">	
	            	<petclinic:selectField label="Genre" name="genre" size="2" names="${genres}"/>
	            </div>
	            <petclinic:inputField label="Telephone" name="telephone"/>
	            <petclinic:inputField label="Username" name="username"/>
	            <petclinic:inputField label="Password" name="password"/>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">
	                <c:choose>
	                    <c:when test="${user['new']}">
	                        <button class="btn btn-default" type="submit">Register</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class="btn btn-default" type="submit">Edit</button>
	                    </c:otherwise>
	                </c:choose>
	            </div>
	        </div>
	    </form:form>
	</jsp:body>
</petclinic:layout>
