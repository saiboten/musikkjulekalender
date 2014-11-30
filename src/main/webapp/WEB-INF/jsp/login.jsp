<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js">
  </script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer>
  </script>
  
  
  <script>
  function statusChangeCallback(response) {
    if (response.status === 'connected') {
    	$.ajax({
    		type: "POST",
    		url: "/facebooklogin",
    		data: response.authResponse.accessToken,
    		success: function(response) {
    			console.log("Response from server: ", response.result);
    			if(response.result) {
    				document.getElementById('status').style.display = "";
    				document.getElementById('status').innerHTML = 'Du er logget inn med Facebook!';
    				document.getElementById('signinButton').style.display = 'none';
    			}
    		},
    		dataType: "json",
    		contentType: "application/json"
    		});
    	
    } else if (response.status === 'not_authorized') {
      document.getElementById('status').innerHTML = 'Logg inn med Facebook. Klikk Log inn-knappen!';
    } else {
      document.getElementById('status').innerHTML = 'Du må logge inn på facebook for å logge inn på musikkjulekalenderen!';
    }
  }

  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
	  FB.init({
	    appId      : '258625720859950',
	    cookie     : true,  // enable cookies to allow the server to access 
	                        // the session
	    xfbml      : true,  // parse social plugins on this page
	    version    : 'v2.1' // use version 2.1
	  });
	  
	  FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
	  });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/nb_NO/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->



<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Innlogging</h3>
			  </div>
			  <div class="panel-body">
				<p>Du kan velge mellom å logge inn med Google, eller med Facebook. Dette er kun for å
				holde rede på svarene dine, og vi lover å ikke misbruke
				informasjonen din på noen måte. Vi skal ikke engang sende ut
				plagsomme eposter.</p>
			  </div>
			</div>


	<div id="loginButtons" class="well">
	
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
		
		<fb:login-button id="facebookLoginButton" data-auto-logout-link="true" scope="public_profile,email" onlogin="checkLoginState();"> </fb:login-button>
	</div>
	
	<div id="status" style="display: none;" class="well">
	
	</div>
	
	</div>
</div>
	
	<script>
	function signInCallback(authResult) {
	  if (authResult['code']) {
		  
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
	       		document.getElementById('status').style.display = "";
				document.getElementById('facebookLoginButton').style.display = 'none';
				document.getElementById('loginButtons').style.display = 'none';
	       		$('#status').html("Du er nå logget inn med Google. For å logge ut... tja, gå til google.com og let etter en utloggingsknapp!");
	       	}
	       	else {
	       		document.getElementById('status').style.display = "";
	       		$('#status').html("Noe gikk galt med Google-påloggingen. Prøv igjen senere.");
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