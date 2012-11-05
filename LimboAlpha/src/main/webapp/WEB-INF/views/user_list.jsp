<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="main.jsp" />

		<CENTER>
			<table id="htmlgrid" class="testgrid">
				<tr>	
					<th></th>
					<th><spring:message code="label.user_username" /></th>
					<th><spring:message code="label.user_name" /></th>
					<th><spring:message code="label.user_surname" /></th>		
					<th><spring:message code="label.user_pkcode" /></th>
					<th><spring:message code="label.user_email" /></th>
					<th><spring:message code="label.user_telephone" /></th>
					<th></th>
				</tr>
				<c:forEach items="${userList}" var="user" varStatus="status">
					<tr>
						<td><a href="<c:url value="/user/update/${user.id}" />"><img src="${pageContext.request.contextPath}/images/edit_icon.png" alt="EDIT"></a></td>
						<td>${user.username}</td>
						<td>${user.name}</td>
						<td>${user.surname}</td>
						<td>${user.pkCode}</td>
						<td>${user.email}</td>
						<td>${user.telephone}</td>
						<sec:authorize ifAllGranted="ROLE_ADMIN">
							<td><a href="<c:url value="/user/delete/${user.id}" />"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></a></td>	
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</CENTER>
	</body>
</html>