<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="main.jsp" />
		<div class="container">
			<form:form method="post" action="add" commandName="repair"
				modelAttribute="repair" class="form-horizontal" onsubmit="onReapirSubmit()">
				<form:errors path="*" cssClass="errorblock" element="div" />
				<CENTER>
					<c:if test="${repairId > 0}">
						<h1>
							<spring:message code="label.repair_id" />
							: ${repairId}
						</h1>
					</c:if>
		
					<fieldset>
						<legend>Информация о клиенте</legend>
						<div class="control-group">
							<label class="control-label"  for="clientFullName"><spring:message code="label.repair_clientFullName" />:</label>
							<form:input path="clientFullName" required="required" />
						</div>
						<div class="control-group">	
							<label class="control-label" for="clientMobileNumber"><spring:message code="label.repair_clientMobileNumber" />:</label>
							<form:input path="clientMobileNumber" required="required" />
						</div>
					</fieldset>
					
					<fieldset>
						<legend>Информация о телефоне</legend>
						<div class="control-group">
							<label class="control-label" for="phoneManufacturer" ><spring:message code="label.repair_clientFullName" />:</label>
							<form:input path="phoneManufacturer" />
						</div>
						<div class="control-group">
							<label class="control-label" for="phoneModel" ><spring:message code="label.repair_phoneModel" />:</label>
							<form:input path="phoneModel" />
						</div>
						
						<div class="control-group">
							<label class="control-label" for="phoneSecurityCode" ><spring:message code="label.repair_phoneSecurityCode" />:</label>
							<form:input path="phoneSecurityCode" />
						</div>
						<div class="control-group">
							<label class="control-label" for="imei" ><spring:message code="label.repair_phoneIMEI" />:</label>
							<form:input path="imei" />
						</div>
						
						<div class="control-group">
							<label class="control-label" for="warrantyPeriod" ><spring:message code="label.repair_warantyPeriod" />:</label>
							<form:input path="warrantyPeriod" />
						</div>
						<div class="control-group">
							<label class="control-label" for="baterySerialNumber" ><spring:message code="label.repair_batterySerialNumber" />:</label>
							<form:input path="baterySerialNumber" />
						</div>
						
						 <div class="control-group">				    
						 	<label class="checkbox" for="charger"> <spring:message code="label.repair_charger" />:</label>
							<form:checkbox path="charger" />
						</div>
						<form:label path="complains">
							<spring:message code="label.repair_complains" />
						</form:label>
						<form:textarea path="complains" rows="10" cols="100" cssStyle="width: 500px;" />
						<br />
						<br />
					</fieldset>
					<fieldset>
						<legend>Информация о ремонте</legend>
						<div class="control-group">
							<label class="control-label" for="receiptDate" ><spring:message code="label.repair_receiptDate" />:</label>
							<form:input path="receiptDate" class="datepicker" />
						</div>
						<div class="control-group">
							<label class="control-label" for="repairDate" ><spring:message code="label.repair_repairDate" />:</label>
							<form:input path="repairDate" class="datepicker" />
						</div>
						<div class="control-group">
							<label class="control-label" for="paymentAmount" ><spring:message code="label.repair_paymentAmount" />:</label>
							<form:input path="paymentAmount" placeholder="0.00" pattern="[0-9]*[\.|\,][0-9]{2}" />
						</div>				
					</fieldset>
					<form:hidden path="id" />
					<form:hidden path="phone" />
					<form:hidden path="battery" />
					<form:hidden path="returned" />
					<form:hidden path="returnDate" />
					<form:hidden path="warranty" />
					<form:hidden path="userId" />	
					<input type="submit" class="btn btn-large"
						<c:choose>
							<c:when test="${repairId > 0}" > value="<spring:message code="label.repair_update"/>" </c:when>
							<c:otherwise> value="<spring:message code="label.repair_addNew"/>" </c:otherwise> 
						</c:choose> 
					/>	
				</CENTER>
			</form:form>
		</div>
		<div id="push"></div>
	</div>	
	<div id="footer">
	  	<div class="container">
		    <p class="muted credit"><spring:message code="label.footer" /></p>
		</div>
	</div>	
	<script type="text/javascript">
	function onReapirSubmit(){
		var amount = $("#paymentAmount");
		if (amount.val() != ""){
			  amount.val(amount.val().replace(/[^0-9]/gi, ""));		
		  }
	}	
	</script>
</body>
</html>