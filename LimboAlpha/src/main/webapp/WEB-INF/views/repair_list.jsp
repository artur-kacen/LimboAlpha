<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="main.jsp" />


<h3><a href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /> </a></h3>
<c:if test="${!empty repairList}">
	<table class="data">
		<tr>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
			<th><spring:message code="label.repair_id" /></th>
			<th><spring:message code="label.repair_clientFullName" /></th>
			<th><spring:message code="label.repair_clientMobileNumber" /></th>
			<th><spring:message code="label.repair_complains" /></th>		
			<th><spring:message code="label.repair_receiptDate" /></th>
			<th><spring:message code="label.repair_repairDate" /></th>
			<th><spring:message code="label.repair_phoneManufacturer" /></th>
			<th><spring:message code="label.repair_phoneModel" /></th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${repairList}" var="repair" varStatus="status">
			<tr <c:choose><c:when test="${repairService.isReturned(repair)}"> class="returned_items"</c:when><c:otherwise> class="line_items"</c:otherwise></c:choose>>
				<c:choose>
					<c:when test="${!repairService.isReturned(repair)}">
						<td><a href="<c:url value="/repair/approve/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/approve_icon.png" alt="approve"></a></td>
					</c:when>
					<c:otherwise> 
						<td>&nbsp;</td>
					</c:otherwise>
				</c:choose>
				<td><a href="<c:url value="/repair/getpdf/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/pdf_icon.png" alt="get pdf"></a></td>
				<td><a href="<c:url value="/repair/update/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/edit_icon.png" alt="EDIT"></a></td>
				<td><input name="contacts[${status.index}].id" value="${repair.id}"/></td>
				<td><input name="contacts[${status.index}].clientFullName" value="${repair.clientFullName}"/></td>
				<td><input name="contacts[${status.index}].clientMobileNumber" value="${repair.clientMobileNumber}"/></td>
				<td><input name="contacts[${status.index}].complains" value="${repair.complains}"/></td>
				<td><input name="contacts[${status.index}].receiptDate" value="${repair.receiptDate}"/></td>
				<td><input name="contacts[${status.index}].repairDate" value="${repair.repairDate}"/></td>
				<td><input name="contacts[${status.index}].phoneManufacturer" value="${repair.phoneManufacturer}"/></td>
				<td><input name="contacts[${status.index}].phoneModel" value="${repair.phoneModel}"/></td>
				<sec:authorize ifAllGranted="ROLE_ADMIN,ROLE_SUPERADMIN">
					<td><a href="<c:url value="/repair/delete/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></a></td>	
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>