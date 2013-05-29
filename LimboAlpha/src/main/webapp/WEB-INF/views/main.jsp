<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet" type="text/css" />
	<!--  <link href="${pageContext.request.contextPath}/css/menu.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />-->
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/datatablecustom.css" rel="stylesheet" type="text/css" />
	
	<style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */

      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }

      /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



      /* Custom page CSS
      -------------------------------------------------- */
      /* Not required for template or sticky footer method. */

      #wrap > .container {
        padding-top: 60px;
      }
      .container .credit {
        margin: 20px 0;
      }

      code {
        font-size: 80%;
      }      
    </style>
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />
    
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script>
		$(function() {
			$(".datepicker").datepicker({
				dateFormat : 'yy-mm-dd'
			});
		});	
	</script>
		
	<title><spring:message code="label.title" /></title>		
</head>
<%-- <% String s="#{sec:areAllGranted('ROLE_FOO, ROLE_BAR')}"; %> --%>
<body>
	<div id="wrap">
	 	<div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container-fluid">
	          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="brand" href="#">TexMobile</a>
	       <%--    <a href="<c:url value="/logout" />"> <spring:message
						code="label.logout" />
				</a> <br /> <span style="float: right"> <a href="?lang=ru">ru</a>
					| <a href="?lang=lv">lv</a>
				</span> --%>
	          <div class="nav-collapse collapse">
	          
	            <p class="navbar-text pull-right">
	            	<form:select path="" multiple="false">
						<form:options items="#{sec:property('allowedDatabases')}" />			
					</form:select>
	            	<a href="?lang=ru"><img src="${pageContext.request.contextPath}/img/ru.png" /></a>
	            	<a href="?lang=lv"><img src="${pageContext.request.contextPath}/img/lv.png" /></a>
	             	<spring:message code="label.header_welcome" /> <a href="#" class="navbar-link"> <i class="icon-user icon-white"></i> <u><sec:authentication property="principal.name" /> <sec:authentication property="principal.surname" /> </u></a>
	            </p>
	            <ul class="nav">
	            <li class="dropdown">                    
	                    <a class="dropdown-toggle" data-toggle="dropdown" tabindex="-1" href="<c:url value="/repair" />">
	                    	<spring:message code="label.header_repair" />
	                   	</a>
	                    <ul class="dropdown-menu">
	                    	<li><a tabindex="-1" href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /></a></li>
	                    	<li class="divider"></li>
	                      	<li><a tabindex="-1" href="<c:url value="/repair/list" />"><spring:message code="label.repair_all" /></a></li>
							<li><a tabindex="-1" href="<c:url value="/repair/list?show=new" />"><spring:message code="label.repair_new_items" /></a></li>
							<li><a tabindex="-1" href="<c:url value="/repair/list?show=repaired" />"><spring:message code="label.repair_repaired" /></a></li>
							<li><a tabindex="-1" href="<c:url value="/repair/list?show=returned" />"><spring:message code="label.repair_returned" /></a></li>
							<sec:authorize ifAllGranted="ROLE_ADMIN">
								<li><a tabindex="-1" href="<c:url value="/repair/list?show=deleted" />"><spring:message code="label.repair_deleted" /></a></li>
							</sec:authorize>
	                    </ul>
	            </li>
	              <li><a href="<c:url value="/shop" />"><spring:message code="label.header_shop" /></a></li>
	              <sec:authorize ifAllGranted="ROLE_ADMIN">
						<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" tabindex="-1" href="<c:url value="/user" />">
							<spring:message code="label.header_users" />
						</a>
						<ul class="dropdown-menu">
							<li><a tabindex="-1" href="<c:url value="/user/add" />"> <spring:message code="label.user_new" /></a></li>
							<li class="divider"></li>
							<li><a tabindex="-1" href="<c:url value="/user/list" />"><spring:message code="label.user_list" /></a>
						</ul></li>
					</sec:authorize>
					<sec:authorize ifAllGranted="ROLE_SUPERADMIN">
						<li><a href="<c:url value="/clients" />"><spring:message code="label.header_clients" /></a></li>
					</sec:authorize>
					<li><a href="<c:url value="/contacts" />"><spring:message code="label.header_contacts" /></a></li>
	            </ul>
	            <%-- <form class="navbar-search pull-right">
				  <input type="text" class="search-query" placeholder="Search">
				</form> --%>
	          </div><!--/.nav-collapse -->
	        </div>
	      </div>
	    </div>
	
		<%--<div align="right">
			<a href="<c:url value="/logout" />"> <spring:message
					code="label.logout" />
			</a> <br /> <span style="float: right"> <a href="?lang=ru">ru</a>
				| <a href="?lang=lv">lv</a>
			</span>
		</div>
		 <nav id="menu-wrap">
			<ul id="menu">
				<li><a href="<c:url value="/repair" />"><spring:message	code="label.header_repair" /></a>
					<ul>
						<li><a href="<c:url value="/repair/add" />"> <spring:message code="label.repair_new" /></a></li>
						<li><a href="<c:url value="/repair/list" />"><spring:message code="label.repair_list" /></a>
							<ul>
								<li><a href="<c:url value="/repair/list" />"><spring:message code="label.repair_all" /></a></li>
								<li><a href="<c:url value="/repair/list?show=new" />"><spring:message code="label.repair_new_items" /></a></li>
								<li><a href="<c:url value="/repair/list?show=repaired" />"><spring:message code="label.repair_repaired" /></a></li>
								<li><a href="<c:url value="/repair/list?show=returned" />"><spring:message code="label.repair_returned" /></a></li>
								<sec:authorize ifAllGranted="ROLE_ADMIN">
									<li><a href="<c:url value="/repair/list?show=deleted" />"><spring:message code="label.repair_deleted" /></a></li>
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
		</nav> --%>