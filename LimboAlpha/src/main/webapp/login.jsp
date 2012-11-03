<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<link href="/LimboAlpha/css/main.css" rel="stylesheet" type="text/css" />
	
</head>
<body onload="document.f.j_username.focus()" >
	<c:if test="${not empty param.error}">
		<div class="errorblock">
			<spring:message code="label.loginerror" />
			: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<form class="form-container" name="f" method="POST" action="<c:url value="/j_spring_security_check" />">
		<div class="form-title">
			<h2></h2>
		</div>
		<div class="form-title"><spring:message code="label.nickname" /></div>
		<input class="form-field" type="text" name="j_username"  name="firstname" /><br />
		<div class="form-title"><spring:message code="label.password" /></div>
		<input type="password" class="form-field" name="j_password" /><br />
		
		<div class="submit-container">
			<input class="submit-button" type="submit" value="<spring:message code="label.login"/>"  />
			<div class="form-remember-title"><spring:message code="label.remember"/>
				<input type="checkbox" name="_spring_security_remember_me" />
			</div>			
		</div>
		
	</form>
</body>
</html>