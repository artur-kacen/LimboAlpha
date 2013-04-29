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

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/editablegrid.css" type="text/css" media="screen">
		
	<style>
		body { font-family:'lucida grande', tahoma, verdana, arial, sans-serif; font-size:11px; }
		h1 { font-size: 15px; }
		a { color: #548dc4; text-decoration: none; }
		a:hover { text-decoration: underline; }
		table.testgrid { border-collapse: collapse; border: 1px solid #CCB; width: 1100px; }
		table.testgrid td, table.testgrid th { padding: 5px; border: 1px solid #E0E0E0; }
		table.testgrid th { background: #E5E5E5; text-align: center; }
		input.invalid { background: red; color: #FDFDFD; }
	</style>	

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
	<script src="${pageContext.request.contextPath}/css/jquery-1.8.2.js"></script>
	<script src="${pageContext.request.contextPath}/css/jquery-ui.js"></script>
	<link href="${pageContext.request.contextPath}/css/menu.css"
		rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/main.css"
		rel="stylesheet" type="text/css" />
	<title><spring:message code="label.title" /></title>
	
	<script>
		$(function() {
			$(".datepicker").datepicker({
				dateFormat : 'yy-mm-dd'
			});
		});
		function onSubmitRepair(){
			var amount = document.forms["repair"]["paymentAmount"].value;
			if (amount != ""){			
				amount = amount.replace(/[^0-9]/gi, "");
				document.forms["repair"]["paymentAmount"].value = amount;			
			}
		}
		function replaceRepairAmount(){
			var amount = document.forms["repair"]["paymentAmount"].value;
			if (amount != ""){	
				amount = amount.slice(0, amount.length - 2) + "." + amount.slice(amount.length - 	2, amount.length);
				document.forms["repair"]["paymentAmount"].value = amount;	
			}
		}
	</script>
</head>
<body onload="replaceRepairAmount()">


	<div align="right">
		<a href="<c:url value="/logout" />"> <spring:message
				code="label.logout" />
		</a> <br /> <span style="float: right"> <a href="?lang=ru">ru</a>
			| <a href="?lang=lv">lv</a>
		</span>
	</div>

	<br />	

	<nav id="menu-wrap">
	<ul id="menu">
		<li><a href="<c:url value="/repair" />"><spring:message	code="label.header_repair" /></a>
			<ul>
				<li><a href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /></a></li>
				<li><a href="<c:url value="/repair/list" />"><spring:message code="label.repair_list" /></a>
					<ul>
						<li><a href="<c:url value="/repair/list/all" />"><spring:message code="label.repair_all" /></a></li>
						<li><a href="<c:url value="/repair/list/new" />"><spring:message code="label.repair_new_items" /></a></li>
						<li><a href="<c:url value="/repair/list/repaired" />"><spring:message code="label.repair_repaired" /></a></li>
						<li><a href="<c:url value="/repair/list/returned" />"><spring:message code="label.repair_returned" /></a></li>
						<sec:authorize ifAllGranted="ROLE_ADMIN">
							<li><a href="<c:url value="/repair/list/deleted" />"><spring:message code="label.repair_deleted" /></a></li>
						</sec:authorize>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="#"><spring:message code="label.header_shop" /></a></li>			
		<sec:authorize ifAllGranted="ROLE_ADMIN">
			<li><a href="<c:url value="/user" />"><spring:message code="label.header_users" /></a>
			<ul>
				<li><a href="<c:url value="/user/add" />"> <spring:message code="label.user_new" /></a></li>
				<li><a href="<c:url value="/user/list" />"><spring:message code="label.user_list" /></a>
			</ul></li>
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
			<li><a href="<c:url value="/clients" />"><spring:message code="label.header_clients" /></a></li>
		</sec:authorize>
		<li><a href="">Contact</a></li>
	</ul>
	</nav>