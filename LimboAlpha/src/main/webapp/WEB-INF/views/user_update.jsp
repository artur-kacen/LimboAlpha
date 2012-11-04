<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="main.jsp" />

<form:form method="post" action="add" commandName="user">
	<form:errors path="*" cssClass="errorblock" element="div" />
	<br />
	<table>
		<tr>
			<td colspan="2"><spring:message code="label.user_loginfo" /></td>
		</tr>
		<tr >
			<td><form:label path="username">
					<spring:message code="label.user_username" />
				</form:label></td>
			<td><form:input path="username" /></td>
		</tr>
		<tr>
			<td><form:label path="password">
					<spring:message code="label.user_password" />
				</form:label></td>
			<td><form:password path="password" /></td>
		</tr>
	</table>
	
	<br />
	
	<table>
		<tr>
			<td colspan="2"><spring:message code="label.user_info" /></td>
		</tr>
		<tr >
			<td><form:label path="name">
					<spring:message code="label.user_name" />
				</form:label></td>
			<td><form:input path="name" /></td>
		</tr>
		<tr >
			<td><form:label path="surname">
					<spring:message code="label.user_surname" />
				</form:label></td>
			<td><form:input path="surname" /></td>
		</tr>
		<tr >
			<td><form:label path="pkCode">
					<spring:message code="label.user_pkcode" />
				</form:label></td>
			<td><form:input path="pkCode" /></td>
		</tr>
		<tr >
			<td><form:label path="email">
					<spring:message code="label.user_email" />
				</form:label></td>
			<td><form:input path="email" /></td>
		</tr>
		<tr >
			<td><form:label path="telephone">
					<spring:message code="label.user_telephone" />
				</form:label></td>
			<td><form:input path="telephone" /></td>
		</tr>
	</table>
	<form:hidden path="id" />
	<input type="submit" value="<spring:message code="label.repair_update"/>" />
</form:form>


</body>
</html>