<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title">Svarlagring</h2>

			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
			<p>${feedback}</p>
			<p><a href="/">Tilbake til forsiden</a></p></div>
		</div>

		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end #page -->

<%@ include file="after.jsp"%>