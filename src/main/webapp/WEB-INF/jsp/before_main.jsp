<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="shortcut icon" href="/static/favicon.ico" />

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<title>Julekalender - Musikkquiz!</title>
<link rel="stylesheet" type="text/css" href="/static/jquery-ui-1.11.2/jquery-ui.min.css" />

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/custom.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-70967404-1', 'auto');
  ga('send', 'pageview');

</script>

</head>
<body>


<!-- Svaret på dagens spørsmålet ligger skjult i denne koden: 0101010001110010011011110111001000100000011001000111010100100000011101100110100101110010011010110110010101101100011010010110011100100000011010100110010101100111001000000111001011111000011100000110010101110010001000000111001101110110011000010111001001100101011101000010000001101001001000000110101101101001011011000110010001100101011010110110111101100100011001010110111000111111001000000100001101101111011011010110010100100000011011110110111000100001 -->
	<div class="container" id="wrapper">


		<nav class="navbar navbar-default" role="navigation">
		  <div>
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Musikkjulekalender 2015</a>
		    </div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

		       <ul class="nav navbar-nav">
            <li class="${frontpage}">
              <a href="/">
                Forsiden
              </a>
            </li>
              <li class="${about}">
                  <a href="/om">
                      Om kalenderen
                  </a>
              </li>
              <li class="${logmein}">
                  <a href="/logmeon">
                      Logg inn/ut
                  </a>
              </li>
          </ul>

		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
