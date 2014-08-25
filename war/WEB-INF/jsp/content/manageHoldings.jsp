<script type="text/javascript">
	swfobject.registerObject("manageHoldingsPortlet", "9.0.0", "expressInstall.swf");
</script>

<div id="flashcontent" style="width: 98%; margin-left: auto; margin-right: auto; height: 100%">
	<object id="manageHoldingsPortlet" data="<%= request.getContextPath() %>/swfs/manageHoldings.swf" type="application/x-shockwave-flash" width="100%" height="100%">
        <param name="allowScriptAccess" value="sameDomain">
        <param name="quality" value="best">
        <param name="wmode" value="transparent">
        <embed src="" quality="high" wmode="transparent" pluginspage="http://www.adobe.com/go/getflash" type="application/x-shockwave-flash" width="100%" height="100%"></embed>
        <param name="movie" value="<%= request.getContextPath() %>/swfs/manageHoldings.swf" />
        <param name="FlashVars" value="userName=<%= request.getUserPrincipal().getName() %>&viewIndex=${viewIndex}&accountNumber=${accountNumber}" />

        <!--[if !IE]>-->
        <object type="application/x-shockwave-flash" data="<%= request.getContextPath() %>/swfs/manageHoldings.swf" width="100%" height="100%"
        	FlashVars="userName=<%= request.getUserPrincipal().getName() %>&viewIndex=${viewIndex}&accountNumber=${accountNumber}">
        <!--<![endif]-->
        <p>Sorry, browser does not support Shockwave Flash content</p>
        <!--[if !IE]>-->
        </object>
        <!--<![endif]-->
    </object>
</div>