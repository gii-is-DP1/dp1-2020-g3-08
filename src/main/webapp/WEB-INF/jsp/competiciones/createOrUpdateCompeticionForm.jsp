<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="competiciones">

    <h2>
        <c:if test="${competicion['new']}">Nueva </c:if> Competici&oacute;n
    </h2>
    <form:form modelAttribute="competicion" class="form-horizontal" id="add-competicion-form">

        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre de la competición" name="nombreComp"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">A&ntilde;adir Competici&oacute;n</button>
            </div>
        </div>
    </form:form>

</petclinic:layout>
