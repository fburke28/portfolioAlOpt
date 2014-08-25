<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<div style="width: 95%; margin-left: auto; margin-right: auto;">
	<table width="100%" height="165">
		<tr height="95">
			<td rowspan="2"><img src="<%=request.getContextPath()%>/images/welcomeLogo.png" /></td>
			<td valign="bottom">
				<img src="<%=request.getContextPath()%>/images/welcomeText.jpg" />
			</td>
		</tr>
		<tr height="70">
			<td valign="top">
				This site provides you with the ability to manage and simulate a portfolio of investments in the stock market. 
				At the end of each trading day the system will provide a list of changes to your portfolio to optimize investment return.
				To view your portfolio details and any available optimization results please login. 
				If you do not have an account and want to register please click on 
				the <a id="" href="<%=request.getContextPath()%>/app/home/register" style="color: blue; ">sign-up</a> link.
				<c:if test="${not empty param.login_error}">
				   	<font color="red">
				       	<br />Your login attempt was not successful, please try again. Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
				   	</font>
				</c:if>
			</td>
		</tr>
	</table>

	<object id="welcomePortlet" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="400">
	    <param name="movie" value="<%= request.getContextPath() %>/swfs/RSSViewer.swf" />
	    <param name="FlashVars" value="contextPath=<%=request.getContextPath()%>" />

	    <!--[if !IE]>-->
	    <object type="application/x-shockwave-flash" data="<%= request.getContextPath() %>/swfs/RSSViewer.swf" width="100%" height="400"
	    	FlashVars="contextPath=<%=request.getContextPath()%>">
	    <!--<![endif]-->
	        <p>Sorry, browser does not support Shockwave Flash content</p>
	    <!--[if !IE]>-->
	    </object>
	    <!--<![endif]-->
	</object>

</div>