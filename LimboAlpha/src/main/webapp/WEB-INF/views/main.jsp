<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<!--  	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>"/>-->
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<title><spring:message code="label.title" /></title>
</head>
<body>



<a href="<c:url value="/logout" />">
	<spring:message code="label.logout" />
</a>
  
<ul id="css3menu1" class="topmenu">
	<li class="topfirst"><a href="#" style="height:18px;line-height:18px;"><span><spring:message code="label.header_repair" /></span></a><ul>
		<li><a href="<c:url value="/repair/add" />" >Add</a></li>
		<li><a  href="<c:url value="/repair/list" />" >List</a></li>
	</ul></li>
	<li class="topmenu"><a href="#" style="height:18px;line-height:18px;"><span><spring:message code="label.header_shop" /></span></a>
	<ul>
		<li><a href="#">test</a></li>
		<li><a href="#">test2</a></li>
	</ul></li>
	<sec:authorize ifAllGranted="ROLE_ADMIN">
		<li class="toplast"><a href="<c:url value="/clients" />"style="height:18px;line-height:18px;"><spring:message code="label.header_clients" /></a></li>
	</sec:authorize>
</ul>


</body>
</html>