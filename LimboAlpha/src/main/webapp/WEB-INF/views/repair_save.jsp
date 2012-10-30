<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="main.jsp" />


<form:form method="post" action="add" commandName="repair">
	<form:label path="phone">
		<spring:message code="label.repair_phone" />
	</form:label>
	<br />
	<table>
		<tr>
			<td><form:label path="phoneManufacturer">
					<spring:message code="label.repair_phoneManufacturer" />
				</form:label></td>
			<td><form:label path="phoneModel">
					<spring:message code="label.repair_phoneModel" />
				</form:label></td>
		</tr>
		<tr>
			<td><form:input path="phoneManufacturer" /></td>
			<td><form:input path="phoneModel" /></td>
		</tr>
		<tr>
			<td><form:label path="phoneSecurityCode">
					<spring:message code="label.repair_phoneSecurityCode" />
				</form:label></td>
			<td><form:label path="imei">
					<spring:message code="label.repair_phoneIMEI" />
				</form:label></td>
		</tr>
		<tr>
			<td><form:input path="phoneSecurityCode" /></td>
			<td><form:input path="imei" /></td>
		</tr>
		<tr>
			<td><form:label path="baterySerialNumber">
					<spring:message code="label.repair_batterySerialNumber" />
				</form:label></td>
		</tr>
		<tr>
			<td><form:input path="baterySerialNumber" /></td>
		</tr>
	</table>
	
	<br />
	<table>
		<tr>
			<td><form:label path="clientFullName">
					<spring:message code="label.repair_clientFullName" />
				</form:label></td>
			<td><form:label path="clientMobileNumber">
					<spring:message code="label.repair_clientMobileNumber" />
				</form:label></td>
			<td><form:label path="receiptDate">
					<spring:message code="label.repair_receiptDate" />
				</form:label></td>
			<td><form:label path="repairDate">
					<spring:message code="label.repair_repairDate" />
				</form:label></td>
		</tr>
		<tr>
			<td><form:input path="clientFullName" /></td>
			<td><form:input path="clientMobileNumber" /></td>
			<td><form:input path="receiptDate" class="datepicker"/></td>
			<td><form:input path="repairDate"  class="datepicker"/></td>
		</tr>
		<tr>
			<td><form:label path="warranty">
					<spring:message code="label.repair_waranty" />
				</form:label></td>
			<td><form:label path="warrantyPeriod">
					<spring:message code="label.repair_warantyPeriod" />
				</form:label></td>
			<td><form:label path="paymentAmount">
					<spring:message code="label.repair_paymentAmount" />
				</form:label></td>
		</tr>
		<tr>
			<td><form:checkbox path="warranty" /></td>
			<td><form:input path="warrantyPeriod" /></td>
			<td><form:input path="paymentAmount" /></td>
		</tr>
	</table>
	<form:label path="complains">
		<spring:message code="label.repair_complains" />
	</form:label>
	<br />
	<form:textarea path="complains" rows="10" cols="50"/>
	<br />
	<input type="submit" value="<spring:message code="label.repair_update"/>" />
</form:form>


</body>
</html>
