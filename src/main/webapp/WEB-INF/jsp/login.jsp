<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
  </script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer>
  </script>

<div id="page">
	<div id="fullcontent">
		<div class="post">
			<h2 class="title">Innlogging</h2>
			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
				<p>Du logger inn med din Googlekonto. Dette er kun for å
					holde rede på svarene dine, og vi lover å ikke misbruke
					informasjonen din på noen måte. Vi skal ikke engang sende ut
					plagsomme eposter.</p>
			</div>
		</div>

		<div class="post">
			<c:choose>
				<c:when test="${loggedIn}">
					<h2 class="title"><a href="${logoutUrl}">Logg ut</a></h2>
				</c:when>
				<c:otherwise>
					<!-- Add where you want your sign-in button to render -->
					<div id="signinButton">
					  <span class="g-signin"
					    data-scope="profile email"
					    data-clientid="814247292614-kvbdepicmv5sbk5ufocb5lf7agcqf907.apps.googleusercontent.com"
					    data-redirecturi="postmessage"
					    data-accesstype="offline"
					    data-cookiepolicy="single_host_origin"
					    data-callback="signInCallback">
					  </span>
					</div>
					<div id="result"></div>
				</c:otherwise>
			</c:choose>
		</div>

		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end #page -->

	<!-- Last part of BODY element in file index.html -->
	<script>
	function signInCallback(authResult) {
	  if (authResult['code']) {
		  
		  console.log("Authresult: ", authResult);
	
	    // Hide the sign-in button now that the user is authorized, for example:
	    $('#signinButton').attr('style', 'display: none');
	
	    // Send the code to the server
	    $.ajax({
	      type: 'POST',
	      url: 'plus?storeToken',
	      contentType: 'application/octet-stream; charset=utf-8',
	      success: function(result) {
	    	  console.log("Raw result: ", result);
	       	if(result.result === "SUCCESS") {
	       		console.log("YES! Success!");
	       		window.location = "/overview";
	       	}
	      },
	      processData: false,
	      data: authResult['code']
	    });
	  } else if (authResult['error']) {
	    // There was an error.
	    // Possible error codes:
	    //   "access_denied" - User denied access to your app
	    //   "immediate_failed" - Could not automatially log in the user
	    // console.log('There was an error: ' + authResult['error']);
	  }
	}
	</script>

<%@ include file="after.jsp"%>