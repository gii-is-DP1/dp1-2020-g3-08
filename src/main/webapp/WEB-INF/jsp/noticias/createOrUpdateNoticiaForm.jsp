<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="noticias">

    <h2>
        <c:if test="${noticia['new']}">Nueva </c:if> Noticia
    </h2>
    <form:form modelAttribute="noticia" class="form-horizontal" id="add-noticia-form">
    	<input type="hidden" name="date" value=""/>
        <div class="form-group has-feedback">
            <petclinic:inputField label="Titulo" name="title"/>
            <petclinic:inputField label="Texto" name="text"/>
             <div class="control-group">	
	            	<petclinic:selectField label="Partido" name="partidos" size="5" names="${partidos}"/>
	         </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Add Noticia</button>
            </div>
        </div>
    </form:form>
 
</petclinic:layout>
