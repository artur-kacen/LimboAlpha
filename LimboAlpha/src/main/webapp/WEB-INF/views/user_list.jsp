<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="main.jsp" />


<h3><a href="<c:url value="/user/add" />"> <spring:message code="label.user_new" /> </a></h3>
<c:if test="${!empty userList}">
	<table class="data">
		<tr>	
			<th>&nbsp;</th>
			<th><spring:message code="label.user_username" /></th>
			<th><spring:message code="label.user_name" /></th>
			<th><spring:message code="label.user_surname" /></th>		
			<th><spring:message code="label.user_pkcode" /></th>
			<th><spring:message code="label.user_email" /></th>
			<th><spring:message code="label.user_telephone" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${userList}" var="user" varStatus="status">
			<tr>
				<td><a href="<c:url value="/user/update/${user.id}" />"><img src="${pageContext.request.contextPath}/images/edit_icon.png" alt="EDIT"></a></td>
				<td><input name="user[${status.index}].username" value="${user.username}"/></td>
				<td><input name="user[${status.index}].name" value="${user.name}"/></td>
				<td><input name="user[${status.index}].surname" value="${user.surname}"/></td>
				<td><input name="user[${status.index}].pkCode" value="${user.pkCode}"/></td>
				<td><input name="user[${status.index}].email" value="${user.email}"/></td>
				<td><input name="user[${status.index}].telephone" value="${user.telephone}"/></td>
				<sec:authorize ifAllGranted="ROLE_ADMIN">
					<td><a href="<c:url value="/user/delete/${user.id}" />"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></a></td>	
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>