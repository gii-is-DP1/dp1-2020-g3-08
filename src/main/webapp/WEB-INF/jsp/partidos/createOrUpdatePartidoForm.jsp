<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidos">

    <h2>
        <c:if test="${partido['new']}">New </c:if> Partido
    </h2>
    <form:form modelAttribute="partido" class="form-horizontal" id="add-partido-form">
    	<input type="hidden" name="date" value=""/>
        <div class="form-group has-feedback">
            <petclinic:inputField label="Lugar" name="lugar"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Add Partido</button>
            </div>
        </div>
    </form:form>
 
</petclinic:layout>
