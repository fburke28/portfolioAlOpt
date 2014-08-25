<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<!-- <link type="text/css" href="<%=request.getContextPath()%>/css/ui-lightness/jquery-ui-1.8.12.custom.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.12.custom.min.js"></script> -->

<style type="text/css">
	a {
		color: white;
		text-decoration: none;
	}

	.footerSep {
		color: #999999;
	}
</style>

<table width="100%" height="35px">
    <tr align="center">
    	<td class="footerSep">
    		<a href="<spring:url value="/po-interface/j_spring_security_logout" htmlEscape="true" />">Logout</a> |
    		<a href="mailto:finbarr.burke@gmail.com?subject=Portfolio optimization tool query.">Contact</a>
    	</td>
    </tr>
</table>

<!-- ui-dialog -->
<!-- <div id="dialog" title="About Application">
	<p>
		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
		Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
	</p>
</div> -->