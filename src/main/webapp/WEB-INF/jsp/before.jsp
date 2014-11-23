<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="/static/jquery-1.7.min.js"></script>
<script type="text/javascript" src="/static/jquery-ui-1.8.16.custom.min.js"></script>
<title>Julekalender - Musikkquiz!</title>
<link rel="stylesheet" type="text/css" href="/static/style.css" />
<link rel="stylesheet" type="text/css" href="/static/jquery-ui-1.8.16.custom.css" />
</head>
<body>
<!-- Svaret på dagens spørsmålet ligger skjult i denne koden: 0101010001110010011011110111001000100000011001000111010100100000011101100110100101110010011010110110010101101100011010010110011100100000011010100110010101100111001000000111001011111000011100000110010101110010001000000111001101110110011000010111001001100101011101000010000001101001001000000110101101101001011011000110010001100101011010110110111101100100011001010110111000111111001000000100001101101111011011010110010100100000011011110110111000100001 -->
	<div id="wrapper">
			<div id="menu-wrapper">			
				<div id="menu">
					<ul>
						<!--   class="current_page_item" -->
						<li><a href="/">Forsiden</a></li>
						<li><a href="/overview">Oversikt og løsninger</a></li>
						<li><a href="/om">Om kalenderen</a></li>
						<li><a href="/login">Logg inn/ut</a></li>
					</ul>
				</div>
			</div>
			<div id="logo">
				<h1>
					<a href="/">Musikkjulekalender</a>
				</h1>
			</div>