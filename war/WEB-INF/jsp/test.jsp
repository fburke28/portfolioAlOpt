<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
    <head>
		<meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
         <title>Parallax Content Slider with CSS3 and jQuery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <meta name="description" content="Parallax Content Slider with CSS3 and jQuery" />
        <meta name="keywords" content="slider, animations, parallax, delayed, easing, jquery, css3, kendo UI" />
        <meta name="author" content="Codrops" />
        <link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome/demo.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome/style2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/slide.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/welcome/modernizr.custom.28468.js"></script>
		<!-- Sliding effect -->
		<script src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/slide.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/stuHover.js" type="text/javascript"></script>
		<link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
		<noscript>
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/welcome/nojs.css" />
		</noscript>
    </head>
    <body>
    	<!-- Panel -->
		<div id="toppanel">
			<div id="panel">
				<div class="content clearfix">
					<div class="left">
						<!-- Login Form -->
						<form class="clearfix" id="loginForm" name="loginForm" action="/j_spring_security_check" method="post">
							<label class="grey" for="log">Username:</label>
							<input name="j_username" id="j_username" type="text" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' size="23" />
							<label class="grey" for="pwd">Password:</label>
							<input name="j_password" id="j_password" type="password" size="23" />
			            	<label><input name="_spring_security_remember_me" type="checkbox" /> &nbsp;Remember me</label>
		        			<div class="clear"></div>
		        			<input type="submit" name="login" value="Login" style="color: white" class="bt_login" />
						</form>
					</div>
					<div class="welcome">
						<h1>Member Login</h1>
						<p class="grey">
							To login to the system please enter your user credentials and click the login button.
						</p>
						<p class="grey">
							If you do not have an account please use the sign-up link on the welcome page where you will be 
							asked to enter your user details to register for an account.
						</p>
					</div>
				</div>
			</div> <!-- /login -->

			<!-- The tab on top -->
			<div class="tab">
				<ul class="login">
					<li class="left">&nbsp;</li>
					<li>Hello Guest!</li>
					<li class="sep">|</li>
					<li id="toggle">
						<a id="open" class="open" href="#">Log In</a>
						<a id="close" style="display: none;" class="close" href="#">Close Panel</a>
					</li>
					<li class="right">&nbsp;</li>
				</ul>
			</div>
		</div>

       	<table width="100%" height="180" style="margin-top: 25px; padding-left: 20px">
			<tr height="95">
				<td rowspan="2"><img src="<%=request.getContextPath()%>/images/welcomeLogo.png" /></td>
				<td valign="bottom">
					<img src="<%=request.getContextPath()%>/images/welcomeText.jpg" />
				</td>
			</tr>
			<tr height="70">
				<td valign="top" class="head">
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
        <div class="container">
        	
			<div id="da-slider" class="da-slider">
				<div class="da-slide">
					<h2>Easy management</h2>
					<p>Register and create an account to manage your portfolio. Select a starting balance and setup account 
					constraints such as the maximum allowable holding percentage in a given sector. View your portfolio
					holdings through an interactive chart with real time trading data.
					</p>
					<div class="da-img"><img src="<%=request.getContextPath()%>/images/welcome/management.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>Informative</h2>
					<p>RSS feeds provide the latest finance information to allow users make an informed decision when 
					selecting trades to execute and optimized results to accept.
					</p>
					<div class="da-img"><img src="<%=request.getContextPath()%>/images/welcome/informative.png" alt="image01" /></div>
				</div>
				<div class="da-slide">
					<h2>Advanced Optimization</h2>
					<p>After each trading day the neural network based classifier runs to evaluate each stocks trading merits, i.e.
					 weather the stock is a buy, hold or sell. The classifier takes as input a user based rating on each stock 
					 weighted towards the more successful user portfolios. The results of an optimization cycle is a set of trades
					 for a user to accept before the start of the next trading day.
					</p>
					<div class="da-img"><img src="<%=request.getContextPath()%>/images/welcome/neuralNet.png" alt="Neural Network" /></div>
				</div>
				<div class="da-slide">
					<h2>Social Interaction</h2>
					<p>A rating rank of the most successful users in the system is continually updated promoting a sense of friendly
					 competition. The more successful a user is the higher the input they have to the classification engine when 
					 rating a stock.
					</p>
					<div class="da-img"><img src="<%=request.getContextPath()%>/images/welcome/socialInteraction.png" alt="image01" /></div>
				</div>
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span>
					<span class="da-arrows-next"></span>
				</nav>
			</div>
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
        <table width="100%" height="125px" style="background: #272727; min-height: 50px; margin-top: 50px">
		    <tr align="center">
		    	<td class="footerSep">
		    		<a href="<spring:url value="/" htmlEscape="true" />">Home</a> |
		    		<a href="<%=request.getContextPath()%>/app/home/register">Sign-up</a> |
		    		<a href="mailto:finbarr.burke@gmail.com?subject=Portfolio optimization tool query.">Contact</a>
		    	</td>
		    </tr>
		</table>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/welcome/jquery.cslider.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#da-slider').cslider({
					autoplay	: false,
					bgincrement	: 40
				});
			});
		</script>
    </body>
</html>