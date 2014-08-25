<div id="copyright" style="display:none">Copyright &copy; 2011 <a href="http://apycom.com/">Apycom jQuery Menus</a></div>

<div id="menu">
	<ul class="menu" style="width: 99%">
		<li><a href="<%= request.getContextPath() %>/app/home/login" class="parent"><span>Home</span></a></li>
		<li><a href="#"><span>Portfolio Management</span></a>
			<div><ul>
				<li><a href="<%= request.getContextPath() %>/app/view/manageHoldings"><span>Manage Holdings</span></a></li>
				<li><a href="#"><span>Optimization Results</span></a></li>
			</ul></div>
		</li>
		<li><a href="#"><span>Security Details</span></a></li>
		<li id="manageAccounts"><a href="#"><span>Manage Account</span></a>
			<div><ul>
				<li><a href="#"><span>User Details</span></a></li>
				<li><a href="#"><span>User Ratings</span></a></li>
			</ul></div>
		</li>
	</ul>
</div>