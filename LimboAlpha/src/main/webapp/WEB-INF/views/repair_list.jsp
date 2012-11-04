<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="main.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editablegrid.css" type="text/css" media="screen">
		
		<style>
			body { font-family:'lucida grande', tahoma, verdana, arial, sans-serif; font-size:11px; }
			h1 { font-size: 15px; }
			a { color: #548dc4; text-decoration: none; }
			a:hover { text-decoration: underline; }
			table.testgrid { border-collapse: collapse; border: 1px solid #CCB; width: 900px; }
			table.testgrid td, table.testgrid th { padding: 5px; border: 1px solid #E0E0E0; }
			table.testgrid th { background: #E5E5E5; text-align: center; }
			input.invalid { background: red; color: #FDFDFD; }
		</style>	
	</head>
	
	<body>
		<CENTER>
		<table id="htmlgrid" class="testgrid">
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th><spring:message code="label.repair_id" /></th>
				<th><spring:message code="label.repair_clientFullName" /></th>
				<th><spring:message code="label.repair_clientMobileNumber" /></th>
				<th><spring:message code="label.repair_complains" /></th>		
				<th><spring:message code="label.repair_receiptDate" /></th>
				<th><spring:message code="label.repair_repairDate" /></th>
				<th><spring:message code="label.repair_phoneManufacturer" /></th>
				<th><spring:message code="label.repair_phoneModel" /></th>
				<th><spring:message code="label.repair_paymentAmount" /></th>
				<sec:authorize ifAllGranted="ROLE_ADMIN">
					<th></th>
				</sec:authorize>	
			</tr>
			<c:forEach items="${repairList}" var="repair" varStatus="status">
			<tr <c:choose><c:when test="${repairService.isReturned(repair)}"> class="returned_items"</c:when><c:when test="${repairService.isRepaired(repair)}"> class="repaired_item" </c:when><c:otherwise> class="line_items"</c:otherwise></c:choose>>
				<c:choose>
					<c:when test="${!repairService.isReturned(repair)}">
						<td><a href="<c:url value="/repair/approve/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/approve_icon.png" alt="approve"></a></td>
					</c:when>
					<c:otherwise> 
						<td></td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${repair.repairDate == null}">
						<td><a href="<c:url value="/repair/repair/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/repair_icon.png" alt="repair" height="24px" width="24px"></a></td>
					</c:when>
					<c:otherwise> 
						<td></td>
					</c:otherwise>
				</c:choose>
				<td><a href="<c:url value="/repair/getpdf/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/pdf_icon.png" alt="get pdf"></a></td>
				<td><a href="<c:url value="/repair/update/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/edit_icon.png" alt="EDIT"></a></td>
				<td>${repair.id}</td>
				<td>${repair.clientFullName}</td>
				<td>${repair.clientMobileNumber}</td>
				<td>${repair.complains}</td>
				<td WIDTH="8%">${repair.receiptDate}</td>
				<td WIDTH="8%">${repair.repairDate}</td>
				<td>${repair.phoneManufacturer}</td>
				<td>${repair.phoneModel}</td>
				<td><fmt:formatNumber type="currency" currencySymbol="Ls" minFractionDigits="2" value="${repair.paymentAmount / 100}"/></td>
				<sec:authorize ifAllGranted="ROLE_ADMIN">
					<td><a href="<c:url value="/repair/delete/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></a></td>	
				</sec:authorize>
			</tr>
		</c:forEach>
		</table>
		</CENTER>
	</body>
</html>