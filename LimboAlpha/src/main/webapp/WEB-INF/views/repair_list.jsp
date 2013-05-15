<%@taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="main.jsp" />
	<!-- <nav id="submenu-wrap">
		<ul id="menu">			
			<li><a href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /></a></li>
			<li><a href="<c:url value="/repair/list/all" />"><spring:message code="label.repair_all" /></a></li>
			<li><a href="<c:url value="/repair/list/new" />"><spring:message code="label.repair_new_items" /></a></li>
			<li><a href="<c:url value="/repair/list/repaired" />"><spring:message code="label.repair_repaired" /></a></li>
			<li><a href="<c:url value="/repair/list/returned" />"><spring:message code="label.repair_returned" /></a></li>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<li><a href="<c:url value="/repair/list/deleted" />"><spring:message code="label.repair_deleted" /></a></li>
			</sec:authorize>				
		</ul>
	</nav> -->
		<CENTER>
		<table id="htmlgrid" class="testgrid">
			<tr>
				<th width="24px"></th>
				<th width="24px"></th>
				<th width="24px"></th>
				<th width="24px"></th>
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
					<th width="24px"></th>
				</sec:authorize>	
			</tr>
			<c:forEach items="${repairList}" var="repair" varStatus="status">
			<tr <c:choose><c:when test="${repairService.isReturned(repair)}"> class="returned_items"</c:when><c:when test="${repairService.isRepaired(repair)}"> class="repaired_item" </c:when><c:otherwise> class="line_items"</c:otherwise></c:choose>>
				<c:choose>
					<c:when test="${!repairService.isReturned(repair)}">
						<td><a href="<c:url value="/repair/approve/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/approve_icon.png" title="<spring:message code="label.returned_record" />" ></a></td>
					</c:when>
					<c:otherwise> 
						<td></td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${repair.repairDate == null}">
						<td><a href="<c:url value="/repair/repair/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/repair_icon.png" title="<spring:message code="label.fixed_record" />" height="24px" width="24px"></a></td>
					</c:when>
					<c:otherwise> 
						<td></td>
					</c:otherwise>
				</c:choose>
				<td><a href="<c:url value="/repair/getpdf/${repair.id}" />" target="_blank"><img src="${pageContext.request.contextPath}/images/pdf_icon.png" title="<spring:message code="label.repair_getpdf" />" ></a></td>
				<td><a href="<c:url value="/repair/update/${repair.id}" />"><img src="${pageContext.request.contextPath}/images/edit_icon.png" title="<spring:message code="label.edit_record" />" ></a></td>
				<td>${repair.id}</td>
				<td WIDTH="10%">${repair.clientFullName}</td>
				<td WIDTH="7%">${repair.clientMobileNumber}</td>
				<td WIDTH="20%">${repair.complains}</td>
				<td WIDTH="8%">${repair.receiptDate}</td>
				<td WIDTH="8%">${repair.repairDate}</td>
				<td>${repair.phoneManufacturer}</td>
				<td>${repair.phoneModel}</td>
				<td><fmt:formatNumber type="currency" currencySymbol="Ls" minFractionDigits="2" value="${repair.paymentAmount / 100}"/></td>
				<sec:authorize ifAllGranted="ROLE_ADMIN">
					<td><a href="<c:url value="/repair/delete/${repair.id}" />" onclick="return confirm('<spring:message code="label.ask_before_delete" />')"><img src="${pageContext.request.contextPath}/images/delete_icon.png" title="<spring:message code="label.delete" />"></a></td>	
				</sec:authorize>
			</tr>
		</c:forEach>
		</table>
		</CENTER>
	</body>
</html>