<script type="text/javascript">
	swfobject.registerObject("accountDetailsPortlet", "9.0.0", "expressInstall.swf");
</script>

<div id="flashcontent" style="width: 98%; margin-left: auto; margin-right: auto; height: 100%">
	<object id="accountDetailsPortlet" data="<%= request.getContextPath() %>/swfs/accountDetails.swf" type="application/x-shockwave-flash" width="100%" height="100%">
        <param name="allowScriptAccess" value="sameDomain">
        <param name="quality" value="best">
        <param name="wmode" value="transparent">
        <embed src="" quality="high" wmode="transparent" pluginspage="http://www.adobe.com/go/getflash" type="application/x-shockwave-flash" width="100%" height="100%"></embed>
        <param name="movie" value="<%= request.getContextPath() %>/swfs/accountDetails.swf" />
        <param name="FlashVars" value="userName=<%= request.getUserPrincipal().getName() %>&accountNumber=${accountNumber}" />

        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="<%= request.getContextPath() %>/swfs/accountDetails.swf" width="100%" height="100%"
        	FlashVars="userName=<%= request.getUserPrincipal().getName() %>&accountNumber=${accountNumber}">
        <!--<![endif]-->
        <p>Sorry, browser does not support Shockwave Flash content</p>
        <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
    </object>
</div>