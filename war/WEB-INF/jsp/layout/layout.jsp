<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- style-sheets -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/slide.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css" />

		<style type="text/css">
			html, body {
				height: 100%;
			}
		</style>

		<script language="javascript">
        	function showAsSelected() {
            	var obj;

                for(i=0; i<4; i++) {  
                	obj = document.getElementById(i);
                    if(obj != null){
                    	removeClass(obj,'current');
                    }
                }
                <c:set var="tabSelected">
                	<tiles:getAsString name="tabSelected" ignore="true"/>
                </c:set>

                <c:choose>
                	<c:when test="${tabSelected == 'home'}">
                    	obj = document.getElementById(0);
                    	addClass(obj,'current');
                    </c:when>
                 	<c:when test="${tabSelected == 'portfolioManagement'}">
                    	obj = document.getElementById(1);
                    	addClass(obj,'current');
                    </c:when>
                    <c:when test="${tabSelected == 'ratingsMaintenance'}">
	            		obj = document.getElementById(2);
	            		addClass(obj,'current');
	            	</c:when>
                    <c:when test="${tabSelected == 'manageAccount'}">
                		obj = document.getElementById(3);
                		addClass(obj,'current');
                	</c:when>
           		</c:choose>                      
			}

			function addClass(element, className) {
           		if (!hasClass(element, className)) {
        	    	var classString = element.className + " " + className; element.className = classString.replace(/^\s*|\s*$/g,'');
        	    }
            }

        	function removeClass(element, className) {
				var classString = element.className.replace(new RegExp("\\b\\s*" + className + "\\b", "g"), "");
				element.className = classString.replace(/^\s*|\s*$/g,'');
        	}

        	function hasClass(element, className) {
        		return (new RegExp("\\b" + className + "\\b")).test(element.className);
			}
        </script>

		<!-- jQuery - the core -->
		<script src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js" type="text/javascript"></script>
		<!-- Sliding effect -->
		<script src="<%=request.getContextPath()%>/js/slide.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/stuHover.js" type="text/javascript"></script>
		<!-- Menu -->
		<script src="<%=request.getContextPath()%>/js/menu.js" type="text/javascript"></script>
		<!-- swfobject -->
		<script src="<%=request.getContextPath()%>/js/swfobject.js" type="text/javascript"></script>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head>
	<body onload='document.loginForm.j_username.focus();'>
		<table width="100%" height="100%">
		    <tr>
		        <td height="3%"><tiles:insertAttribute name="header" /></td>
		    </tr>
		    <tr>
		        <td height="8%" align="center">
			        <div id="copyright" style="display:none">Copyright &copy; 2011 <a href="http://apycom.com/">Apycom jQuery Menus</a></div>
					<div id="menu">
						<ul class="menu" style="width: 99%">
							<li id="0"><a href="<%= request.getContextPath() %>/app/home/login" class="parent"><span>Home</span></a></li>
							<li id="1"><a href="#"><span>Portfolio Management</span></a>
								<div><ul>
									<li><a href="<%= request.getContextPath() %>/app/view/currentHoldings"><span>Current Holdings</span></a></li>
									<li><a href="<%= request.getContextPath() %>/app/view/manageHoldings"><span>Manage Holdings</span></a></li>
									<li><a href="<%= request.getContextPath() %>/app/view/optimizedResults""><span>Optimization Results</span></a></li>
								</ul></div>
							</li>
							<li id="2"><a href="#"><span>Ratings</span></a>
								<div><ul>
									<li><a href="<%= request.getContextPath() %>/app/view/securityRatings"><span>Security Ratings</span></a></li>
									<li><a href="<%= request.getContextPath() %>/app/view/userRatings"><span>User Ratings</span></a></li>
								</ul></div>
							</li>
							<li id="3"><a href="#"><span>Manage Account</span></a>
								<div><ul>
									<li><a href="<%= request.getContextPath() %>/app/view/accountDetails"><span>Account Details</span></a></li>
									<li><a href="<%= request.getContextPath() %>/app/view/userDetails"><span>User Details</span></a></li>
								</ul></div>
							</li>
						</ul>
					</div>
		        </td>
		    </tr>
		    <tr>
		        <td height="81%" style="vertical-align: top;"><tiles:insertAttribute name="body" /></td>
		    </tr>
		    <tr style="background: #272727;">
		        <td height="8%"><tiles:insertAttribute name="footer" /></td>
		    </tr>
		</table>
	</body>
	<script type="text/javascript">
		showAsSelected();
	</script>
</html>