<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="main.jsp" />

<h3><spring:message code="label.repair_list" /></h3>
<c:if test="${!empty repairList}">
	<table class="data">
		<tr>
			<th>&nbsp;</th>
			<th><spring:message code="label.repair_clientFullName" /></th>
			<th><spring:message code="label.repair_clientMobileNumber" /></th>
			<th><spring:message code="label.repair_receiptDate" /></th>
			<th><spring:message code="label.repair_repairDate" /></th>
			<th><spring:message code="label.repair_phoneManufacturer" /></th>
			<th><spring:message code="label.repair_phoneModel" /></th>
			<th><spring:message code="label.repair_phoneSecurityCode" /></th>
			<th><spring:message code="label.repair_phoneIMEI" /></th>
			<th><spring:message code="label.repair_batterySerialNumber" /></th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${repairList}" var="repair" varStatus="status">
			<tr>
			<td><a href="<c:url value="/repair/getpdf/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/pdf_icon.png"></a> </td>
				<td><input name="contacts[${status.index}].clientFullName" value="${repair.clientFullName}"/></td>
				<td><input name="contacts[${status.index}].clientMobileNumber" value="${repair.clientMobileNumber}"/></td>
				<td><input name="contacts[${status.index}].receiptDate" value="${repair.receiptDate}"/></td>
				<td><input name="contacts[${status.index}].repairDate" value="${repair.repairDate}"/></td>
				<td><input name="contacts[${status.index}].phoneManufacturer" value="${repair.phoneManufacturer}"/></td>
				<td><input name="contacts[${status.index}].phoneModel" value="${repair.phoneModel}"/></td>
				<td><input name="contacts[${status.index}].phoneSecurityCode" value="${repair.phoneSecurityCode}"/></td>
				<td><input name="contacts[${status.index}].imei" value="${repair.imei}"/></td>
				<td><input name="contacts[${status.index}].baterySerialNumber" value="${repair.baterySerialNumber}"/></td>		
				<td><a href="<c:url value="/repair/add/${repair.id}" />"><spring:message code="label.edit" /></a></td>	
				<td><a href="<c:url value="/repair/getpdf/${repair.id}" />"><spring:message code="label.repair_getpdf" /></a></td>	
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>