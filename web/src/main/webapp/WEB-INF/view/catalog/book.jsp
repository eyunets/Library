<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<script>
    var reserved = '<spring:message code="book.reserved"/>';
</script>

<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading p-b-0">

			<i class="fa fa-book"></i> ${book.name}

		</div>

		<ul class="list-group list-group-flush">
			<li class="list-group-item"><b><spring:message
						code="catalog.author" />: </b> <c:forEach var="author"
					items="${authors}" varStatus="status">
                    ${author.name} ${author.surname}
                    <c:if test="${not status.last}">,</c:if>
				</c:forEach></li>
			<li class="list-group-item"><b><spring:message
						code="catalog.genre" />: </b> ${book.genre}</li>

			<li class="list-group-item"><b><spring:message
						code="catalog.year" />: </b> ${book.year}</li>

			<li class="list-group-item"><b><spring:message
						code="catalog.isbn"></spring:message>: </b> ${book.isbn}</li>

		</ul>
		<sec:authorize access="hasRole('ADMIN')">
			<div class="panel-body">
				<a
					href="${pageContext.request.contextPath}/books/id=${book.bookID}/edit"
					class="btn btn-primary"> <spring:message code="cabinet.edit" />
				</a>
				<s:form
					action="${pageContext.request.contextPath}/books/id=${book.bookID}/delete"
					method="post" modelAttribute="book">
					<button type="submit" class="btn btn-primary">
						<spring:message code="book.delete" />
					</button>
				</s:form>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('USER')">
			<c:if test="${valid eq 'false'}">
				<div class="panel-body">
					<button class="btn btn-primary bookmark-book" id="${book.bookID}">
						<spring:message code="book.reserve" />
					</button>
				</div>
			</c:if>
			<c:if test="${valid eq 'true'}">
				<button class="btn disabled">
					<spring:message code="book.reserved" />
				</button>
			</c:if>
		</sec:authorize>
	</div>
</div>
