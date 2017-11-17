<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>



<c:if test="${not empty errorMsg and not (errorMsg eq '')}">
	<div class="alert alert-warning alert-dismissible fade show"
		role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<i class="fa fa-times-circle-o"></i>
		</button>
		<spring:message code="data.invalid-rerty" />
	</div>
</c:if>

<div class="modal-dialog modal-sm">
	<div class="modal-content">
		<br>
		<div class="modal-body">
			<div id="myTabContent" class="tab-content">

				<div role="tabpanel" class="tab-pane fade in active show"
					id="signup">
					<p>
						<b><spring:message code="menu.edit" /></b>
					</p>

					<!-- Edit User Form -->

					<s:form class="form-horizontal"
						action="${pageContext.request.contextPath}/users/edit"
						method="post" modelAttribute="user" data-toggle="validator">
						<fieldset>
							<!-- edit user Form -->
							<!-- email
							<div class="form-group">
								<label class="control-label" for="email"><spring:message
										code="login.email" />:</label>
								<div class="controls">
									<s:input type="email" id="email" path="email"
										class="form-control input-large"
										placeholder="New email"
										data-pattern-error="<spring:message bundle='${i18n}' key='data.non-valid'/>"/>
										<s:errors path="email" class="help-inline" />
						
								</div>
							</div>
							-->
							<!--password-->
							<div class="form-group">
								<label class="control-label" for="password"><spring:message
										code="login.password" />:</label>
								<div class="controls">
									<s:input id="password" path="password"
										class="form-control input-large" type="password"
										placeholder="New password"
										data-pattern-error="<spring:message bundle='${i18n}' key='data.non-valid'/>"
										pattern=".{6,30}" />
									<s:errors path="password" class="help-inline" />
								</div>
							</div>

							<br>
							<!-- surname-->
							<div class="form-group">
								<label class="control-label" for="surname"> <spring:message
										code="login.surname" />:
								</label>
								<div class="controls">
									<s:input id="surname" placeholder="New surname"
										class="form-control input-large" path="surname"
										data-pattern-error="<spring:message bundle='${i18n}' key='data.non-valid'/>"
										pattern="^[A-Z]([a-z]){0,29}$" />
									<s:errors path="surname" class="help-inline" />
								</div>
								<small class=" form-text text-muted help-block with-errors">
									<spring:message code='data.less-30' />
								</small>
							</div>


							<!-- name-->
							<div class="form-group">
								<label class="control-label" for="name"></label>
								<div class="controls">
									<s:input id="name" placeholder="New name"
										class="form-control input-large" path="name"
										data-pattern-error="<spring:message code='data.non-valid'/>"
										pattern="^[A-Z][a-z]{0,29}$" />
									<s:errors path="name" class="help-inline" />
								</div>
								<small class=" form-text text-muted help-block with-errors">
									<spring:message code='data.less-30' />
								</small>
							</div>

							<br>

							<!--button-->
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<input type="submit" class="btn btn-success"
										value="<spring:message code="cabinet.edit"/>" />
									<input type="submit" class="btn btn-secondary"
										value="<spring:message code="login.close"/>" />
								</div>
							</div>
						</fieldset>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>