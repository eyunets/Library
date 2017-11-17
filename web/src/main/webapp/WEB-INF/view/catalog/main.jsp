<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<div class="container">
	<c:if test="${not empty Msg and not (Msg eq '')}">
		<c:choose>
			<c:when test="${Msg eq 'Invalid input'}">
				<div class="text-center">
					<h1 class="display-3">
						<spring:message code="data.sorry" />
					</h1>
					<h1 class="display-4">
						<spring:message code="data.non-valid" />
					</h1>
				</div>
			</c:when>
			<c:otherwise>
				<div class="text-center">
					<h1 class="display-3">
						<spring:message code="data.sorry" />
					</h1>
					<h1 class="display-4">
						<spring:message code="data.no-such-books" />
					</h1>
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>

	<div class="row row-margin-bottom">
		<c:forEach var="book" items="${page.getContent()}">
			<div class="col-md-4 no-padding" data-category="view">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">${book.name}</div>
					<div class="panel-body">
						<b> <spring:message code="catalog.genre" />:
						</b> ${book.genre}
					</div>

					<!-- List group -->
					<ul class="list-group list-group-flush">
						<li class="list-group-item"><b><spring:message
									code="catalog.author" />: </b> <c:forEach var="author"
								items="${book.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
							</c:forEach></li>
						<li class="list-group-item"><b><spring:message
									code="catalog.year" />: </b> ${book.year}</li>
					</ul>
					<sec:authorize access="hasRole('ADMIN')">
						<div class="panel-body">
							<a
								href="${pageContext.request.contextPath}/books/id=${book.bookID}"
								class="btn btn-primary"> <spring:message
									code="catalog.info" />
							</a>
						</div>
					</sec:authorize>
					<sec:authorize access="hasRole('BANNED')">
						<div class="panel-body">
							<a href="#" class="btn btn-secondary"> <spring:message
									code="user.banned" />
							</a>
						</div>
					</sec:authorize>
					<sec:authorize access="hasRole('USER')">
						<div class="panel-body">
							<a
								href="${pageContext.request.contextPath}/books/id=${book.bookID}"
								class="btn btn-primary"> <spring:message
									code="catalog.info" />
							</a>
						</div>
					</sec:authorize>
				</div>
			</div>
		</c:forEach>
	</div>


	<c:url var="firstUrl" value="/catalog/books/1" />
	<c:url var="lastUrl" value="/catalog/books/${page.totalPages}" />
	<c:url var="prevUrl" value="/catalog/books/${currentIndex - 1}" />
	<c:url var="nextUrl" value="/catalog/books/${currentIndex + 1}" />

	<ul class="pagination">
		<c:choose>
			<c:when test="${currentIndex == 1}">
				<li class="disabled"><a href="#">&lt;&lt;</a></li>
				<li class="disabled"><a href="#">&lt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${firstUrl}">&lt;&lt;</a></li>
				<li><a href="${prevUrl}">&lt;</a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
			<c:url var="pageUrl" value="/catalog/books/${i}" />
			<c:choose>
				<c:when test="${i == currentIndex}">
					<li class="active"><a href="${pageUrl}"><c:out
								value="${i}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${currentIndex == page.totalPages}">
				<li class="disabled"><a href="#">&gt;</a></li>
				<li class="disabled"><a href="#">&gt;&gt;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${nextUrl}">&gt;</a></li>
				<li><a href="${lastUrl}">&gt;&gt;</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>