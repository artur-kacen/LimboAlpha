<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="main.jsp" />

<form:form method="post" action="add" commandName="repair" onsubmit="onSubmitRepair()"
	modelAttribute="repair">
	<form:errors path="*" cssClass="errorblock" element="div" />
	<CENTER>
	<fieldset>
		<legend>Информация о клиенте</legend>
		<table>
			<tr>
				<td><spring:message code="label.repair_clientFullName" />:</td>
				<td><form:input path="clientFullName" required="required"/></td>
			</tr>
			<tr>
				<td><spring:message code="label.repair_clientMobileNumber" />:
				</td>
				<td><form:input path="clientMobileNumber" required="required"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>Информация о телефоне</legend>
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
				<td><form:label path="warranty">
						<spring:message code="label.repair_warantyPeriod" />
					</form:label></td>
				<td><form:label path="baterySerialNumber">
						<spring:message code="label.repair_batterySerialNumber" />
					</form:label></td>
			</tr>
			<tr>
				<td><form:input path="warrantyPeriod" /></td>
				<td><form:input path="baterySerialNumber" /></td>
			</tr>
			<tr>
				<td><form:label path="charger">
						<spring:message code="label.repair_charger" />
					</form:label></td>
			</tr>
			<tr>
				<td><form:checkbox path="charger" /></td>
			</tr>
		</table>
		<form:label path="complains">
			<spring:message code="label.repair_complains" />
		</form:label>
		</br>
		<form:textarea path="complains" rows="10" cols="50" />
		</br>
	</fieldset>
	<fieldset>
		<legend>Информация о ремонте</legend>
		<table>
			<tr>
				<td><form:label path="receiptDate">
						<spring:message code="label.repair_receiptDate" />
					</form:label></td>
				<td><form:label path="repairDate">
						<spring:message code="label.repair_repairDate" />
					</form:label></td>
				<td><form:label path="paymentAmount">
						<spring:message code="label.repair_paymentAmount" />
					</form:label></td>
			</tr>
			<tr>
				<td><form:input path="receiptDate" class="datepicker" /></td>
				<td><form:input path="repairDate" class="datepicker" /></td>
				<td><form:input path="paymentAmount" pattern="[0-9]*[\.|\,][0-9]{2}" /></td>
			</tr>
		</table>
	</fieldset>
	<form:hidden path="id" />
	<form:hidden path="phone" />
	<form:hidden path="battery" />
	<form:hidden path="returned" />
	<form:hidden path="returnDate" />
	<form:hidden path="warranty" />
	<form:hidden path="userId" />
	<form:hidden path="charger" />
	<input type="submit" value="<spring:message code="label.repair_addNew"/>" />
	</CENTER>
</form:form>


</body>
</html>