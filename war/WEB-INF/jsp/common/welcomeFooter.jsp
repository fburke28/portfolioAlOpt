<%@ include file="/WEB-INF/jsp/includes.jsp" %>

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
    		<a href="<spring:url value="/" htmlEscape="true" />">Home</a> |
    		<a href="<%=request.getContextPath()%>/app/home/register">Sign-up</a> |
    		<a href="mailto:finbarr.burke@gmail.com?subject=Portfolio optimization tool query.">Contact</a>
    	</td>
    </tr>
</table>