<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title><spring:message code="label.title" /></title>
<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/structure.css" rel="stylesheet" type="text/css" />
<link href="/LimboAlpha/css/main.css" rel="stylesheet" type="text/css" />
	
</head>
<body onload="document.f.j_username.focus()" >
	<c:if test="${not empty param.error}">
		<div class="errorblock">
			<spring:message code="label.loginerror" />
			: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<form class="box login" name="f" method="POST" action="<c:url value="/j_spring_security_check" />">
		<fieldset class="boxBody">
		  <label><spring:message code="label.nickname" /></label>
		  <input type="text" tabindex="1" placeholder="<spring:message code="label.nickname"/>" name="j_username"  name="firstname" required>
		  <label><spring:message code="label.password" /></label>
		  <input type="password" name="j_password" tabindex="2" required>
		</fieldset>
		<footer>
		  <label><input type="checkbox" tabindex="3"><spring:message code="label.remember"/></label>
		  <input type="submit" class="btnLogin" value="<spring:message code="label.login"/>"  tabindex="4">
		</footer>
	</form>
</body>
</html>