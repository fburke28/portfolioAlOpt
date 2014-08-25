<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<!-- Panel -->
<div id="toppanel">
	<div id="panel">
		<div class="content clearfix">
			<div class="left">
				<!-- Login Form -->
				<p>
					Details to be added.
				</p>
			</div>
			<div class="welcome">
				<h1>Portfolio Details</h1>
				<p class="grey">
					Details to be added.
				</p>
			</div>
		</div>
	</div> <!-- /login -->

	<!-- The tab on top -->
	<div class="tab">
		<ul class="login">
			<li class="left">&nbsp;</li>
			<li>Welcome: <%= request.getUserPrincipal().getName() %></li>
			<li class="right">&nbsp;</li>
		</ul>
	</div>
</div>