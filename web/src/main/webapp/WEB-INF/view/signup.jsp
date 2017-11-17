<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>


<body>
	<div class="generic-container">

		<s:form method="POST" modelAttribute="user" class="form-horizontal">
			<s:input type="hidden" path="userID" id="id" />
			<div class="row">
				<div class="form-group col-md-9 col-md-offset-3 ">
				<p><spring:message code='login.signup'/></p>
					<label class="col-md-3 control-lable" for="email"><spring:message
									code="login.email" /></label>
					<div class="col-md-6">
						<s:input type="text" path="email" id="email"
							class="form-control input-sm" />
						<div class="has-error">
							<s:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-9 col-md-offset-3 ">
					<label class="col-md-3 control-lable" for="password"><spring:message
									code="login.password" /></label>
					<div class="col-md-6">
						<s:input type="password" path="password" id="password"
							class="form-control input-sm" />
						<div class="has-error">
							<s:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Register"
						class="btn btn-primary btn-sm"
						value="<spring:message code='menu.signin'/>" />

				</div>
			</div>
		</s:form>
	</div>
</body>
</html>