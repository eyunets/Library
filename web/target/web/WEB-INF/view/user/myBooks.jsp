<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<div class="container">
	<c:forEach var="form" items="${forms}">
		<div class="col-md-2 no-padding" id="${form.formID}"
			data-category="view">
			<div class="panel panel-default">
				<div class="panel-heading">${form.book.name}</div>
				<div class="panel-body">
					<p>
						<b> <spring:message code="catalog.genre" />:
						</b> ${form.book.genre}
					</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b><spring:message
								code="catalog.author" />: </b> <c:forEach var="author"
							items="${form.book.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
						</c:forEach></li>
					<li class="list-group-item"><b><spring:message
								code="catalog.year" />: </b> ${form.book.year}</li>
				</ul>
				<div class="panel-body">
					<button class="btn btn-primary return-book" id="${form.formID}">
						<spring:message code="book.return" />
					</button>
				</div>
			</div>
		</div>
	</c:forEach>
</div>