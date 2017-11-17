<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>


<body>
	<div class="container">
		<s:form class="form-horizontal"
			action="${pageContext.request.contextPath}/author/add" method="post"
			modelAttribute="author">
			<fieldset>
				<!-- Add Author Form -->
				<!-- surname-->
				<div class="form-group">
					<label class="control-label" for="surname"> <spring:message
							code="login.surname" />:
					</label>
					<div class="controls">
						<s:input id="surname" class="form-control input-large"
							path="surname" required="true" />
						<s:errors path="surname" class="help-inline" />
					</div>
				</div>

				<!-- name-->
				<div class="form-group">
					<label class="control-label" for="authorname"><spring:message
							code="login.name" />:</label>
					<div class="controls">
						<s:input id="authorname" class="form-control input-large"
							path="name" required="true" />
						<s:errors path="name" class="help-inline" />
					</div>
				</div>

				<!-- second name-->
				<div class="form-group">
					<label class="control-label" for="secondName"><spring:message
							code="login.secondname" />:</label>
					<div class="controls">
						<s:input id="secondName" class="form-control input-large"
							path="secondName" required="true" />
						<s:errors path="secondName" class="help-inline" />
					</div>
				</div>
				<!-- submit -->
				<div class="form-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-success"
							value="<spring:message code='author.add'/>" />
					</div>
				</div>
			</fieldset>
		</s:form>
	</div>
</body>