<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	<s:form class="form-horizontal"
		action="${pageContext.request.contextPath}/books/add" method="post"
		modelAttribute="book">
		<fieldset>
			<legend>
				<b><spring:message code="admin.add.book" /></b>
			</legend>
			<!-- Add Book Form -->
			<!-- name-->
			<div class="form-group">
				<label class="control-label" for="name"> <spring:message
						code="book.name" />:
				</label>
				<div class="controls">
					<s:input id="name" path="name" class="form-control input-large"
						required="true" />
					<s:errors path="name" class="help-inline" />
				</div>
			</div>

			<!--author(s)-->
			<div class="form-group">
				<label class="control-label" for="authors"> <spring:message
						code="catalog.author" />:
				</label>
				<div class="controls">
					<s:select path="authors" multiple="true"
						class="form-control input-sm">
						<c:forEach var="author" items="${authors}">
							<option value="${author.authorID}">${author.name}
								${author.secondName} ${author.surname}</option>
						</c:forEach>
					</s:select>
					<div class="has-error">
						<s:errors path="authors" class="help-inline" />
					</div>
				</div>
				<a href="${pageContext.request.contextPath}/author/add">Add
					author</a>
			</div>


			<br>
			<!--isbn-->
			<div class="form-group">
				<label class="control-label" for="isbn"> <spring:message
						code="catalog.isbn" />:
				</label>
				<div class="controls">
					<s:input id="isbn" class="form-control input-large" path="isbn"
						required="true" />
					<s:errors path="isbn" class="help-inline" />
				</div>
			</div>


			<!--genre-->
			<div class="form-group">
				<label class="control-label" for="genre"> <spring:message
						code="catalog.genre" />:
				</label>
				<div class="controls">
					<s:input id="genre" class="form-control input-large" path="genre"
						required="true" />
					<s:errors path="genre" class="help-inline" />
				</div>
			</div>

			<!--year-->
			<div class="form-group">
				<label class="control-label" for="year"> <spring:message
						code="catalog.year" />:
				</label>
				<div class="controls">
					<s:input id="year" class="form-control input-large" path="year"
						required="true" />
					<s:errors path="year" class="help-inline" />
				</div>
			</div>



			<!--button-->
			<div class="form-group">
				<label class="control-label"></label>
				<div class="controls">
					<input type="submit" class="btn btn-success"
						value="<spring:message code="addbook.title"/>" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>





