	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<petclinic:layout pageName="home">
	<h2>
		<fmt:message key="welcome" />
	</h2>
	<div class="row">
		<h2>Proyecto ${title}</h2>
		<p>
		<h2>Grupo ${group}</h2>
		</p>
		<p>
			<u1> <c:forEach items="${persons}" var="person">
				<li>${person.nombre}${person.apellidos}</li>
			</c:forEach> </u1>
		</p>
		</div>
		<div class="col-md-12">
			<spring:url value="/resources/images/us-logo.png" htmlEscape="true"
				var="usLogo" />
			<img class="img-responsive" src="${usLogo}" />
		</div>
	</div>
</petclinic:layout>
