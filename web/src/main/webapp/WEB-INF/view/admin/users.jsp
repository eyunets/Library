<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script>
    var ban = '<spring:message code="user.ban"/>';
    var unban = '<spring:message code="user.unban"/>';
</script>

<div class="container">
	<c:forEach var="user" items="${users}">
		<div class="col-md-3 no-padding" data-category="view">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="card-title">${user.email}</h4>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><b><spring:message
								code="user.books" />: </b>
						<ul class=" list-group list-unstyled">
							<c:forEach var="form" items="${user.forms}">
								<li class=""><span class="text-danger"><i
										class="fa fa-address-book"></i> ${form.book.name}</span></li>
							</c:forEach>
						</ul></li>
				</ul>
				<div class="panel-body">
					<c:choose>
						<c:when test="${user.userProfile.getType() eq 'BANNED'}">
							<button id="${user.userID}" class="btn btn-success ban-user">
                                <spring:message code="user.unban"/>
                            </button>
						</c:when>
						<c:otherwise>
                            <button id="${user.userID}" class="btn btn-danger ban-user">
                                <spring:message code="user.ban"/>
                            </button>
                        </c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</c:forEach>
</div>