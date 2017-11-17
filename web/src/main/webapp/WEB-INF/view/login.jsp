<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>



<body>
	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<s:form action="${pageContext.request.contextPath}/login"
						method="post" class="form-group">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p><spring:message code='data.lp'/></p>
							</div>
						</c:if>
						<p><spring:message code='menu.signin'/></p>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="email"><i
								class="fa fa-user"></i></label> <input type="text" class="form-control"
								id="email" name="email" placeholder="Enter Email" required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i
								class="fa fa-lock"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>



						<input type="submit" class="btn btn-block btn-primary btn-default"
							value="<spring:message code='menu.signin'/>" />
					</s:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
