<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Musikkjulekalender 2013!</title>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
<script src="/script/christmas-mobile.js"></script>
<script type="text/javascript" src="/audio-player.js"></script>
<script type="text/javascript">
	AudioPlayer.setup("../player.swf", {
		width : 290
	});
</script>
</head>
<body>
	<div data-role="page" id="page1">
		<div id="header" data-theme="a" data-role="header">
			<h3>Kalender 2013!</h3>
		</div>
		<div data-role="content">
			<h5 id="infotext">
				<p>Velkommen til musikkjulekalderen 2013!</p> 
				<p id="log-in-text">For å kunne besvare oppgave må du <a href="/m/login">logge inn</a>.</p>
			</h5>
			<ul id="days" data-role="listview" data-theme="b">

			</ul>
		</div>
	</div>
</body>
</html>