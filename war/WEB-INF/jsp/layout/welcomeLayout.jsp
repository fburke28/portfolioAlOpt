<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- style-sheets -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/slide.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css" />

		<style type="text/css">
			html, body {
				height: 100%;
			}
		</style>

		<!-- jQuery - the core -->
		<script src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js" type="text/javascript"></script>
		<!-- Sliding effect -->
		<script src="<%=request.getContextPath()%>/js/slide.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/stuHover.js" type="text/javascript"></script>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head>
	<body onload='document.loginForm.j_username.focus();'>
		<table width="100%" height="100%">
		    <tr>
		        <td height="5%"><tiles:insertAttribute name="header" /></td>
		    </tr>
		    <tr>
		        <td height="87%" style="vertical-align: top;"><tiles:insertAttribute name="body" /></td>
		    </tr>
		    <tr style="background: #272727; min-height: 50px">
		        <td height="8%"><tiles:insertAttribute name="footer" /></td>
		    </tr>
		</table>
	</body>
</html>