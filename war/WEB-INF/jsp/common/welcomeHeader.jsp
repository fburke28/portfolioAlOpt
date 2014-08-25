<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<style type="text/css">
	.tab a.open, .tab a.close {
		height: 20px;
		line-height: 20px !important;
		padding-left: 30px !important;
		cursor: pointer;
		display: block;
		width: 100px;
		position: relative;
		top: 11px;
	}
</style>

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