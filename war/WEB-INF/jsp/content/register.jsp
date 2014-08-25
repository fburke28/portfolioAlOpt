<script type="text/javascript">
	function load_home() {
		window.location="<%=request.getContextPath()%>/app/home/welcome";
	}
</script>

<div style="width: 100%; margin-left: auto; margin-right: auto; margin-top: 5px; height: 100%">
	<object id="welcomePortlet" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%">
	    <param name="movie" value="<%= request.getContextPath() %>/swfs/registerViewer.swf" />
	    <param name="FlashVars" value="contextPath=<%=request.getContextPath()%>" />

	    <!--[if !IE]>-->
	    <object type="application/x-shockwave-flash" data="<%= request.getContextPath() %>/swfs/registerViewer.swf" width="100%" height="100%"
	    	FlashVars="contextPath=<%=request.getContextPath()%>">
	    <!--<![endif]-->
	        <p>Sorry, browser does not support Shockwave Flash content</p>
	    <!--[if !IE]>-->
	    </object>
	    <!--<![endif]-->
	</object>
</div>