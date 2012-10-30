<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf8">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<title><spring:message code="label.title" /></title>

<script>
	$(function() {
		$(".datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
</head>
<body>


	<div align="right">
		<a href="<c:url value="/logout" />"> <spring:message
				code="label.logout" />
		</a> <br /> <span style="float: right"> <a href="?lang=ru">ru</a>
			| <a href="?lang=lv">lv</a>
		</span>
	</div>

	<br />
	<div align="center">
		<ul id="css3menu1" class="topmenu">
			<li class="topmenu"><a href="#"
				style="height: 18px; line-height: 18px;"><span><spring:message
							code="label.header_repair" /></span></a>
				<ul>
					<li><a href="<c:url value="/repair/add" />"> <spring:message
								code="label.repair_new" /></a></li>
					<li><a href="<c:url value="/repair/list" />"><spring:message
								code="label.repair_list" /></a></li>
				</ul></li>
			<li class="topmenu"><a href="#"
				style="height: 18px; line-height: 18px;"><span><spring:message
							code="label.header_shop" /></span></a></li>
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<li class="topmenu"><a href="<c:url value="/users" />"
					style="height: 18px; line-height: 18px;"><spring:message
							code="label.header_users" /></a></li>
			</sec:authorize>
			<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
				<li class="topmenu"><a href="<c:url value="/clients" />"
					style="height: 18px; line-height: 18px;"><spring:message
							code="label.header_clients" /></a></li>
			</sec:authorize>
		</ul>
	</div>

