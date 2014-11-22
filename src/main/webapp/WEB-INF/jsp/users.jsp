<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title">Brukeroversikt</h2>

			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
			
			<c:forEach var="user" items="${users}" varStatus="rowCounter">
				<p># ${rowCounter.count}: ${user.userName} - Riktige artist: ${user.rightArtist}, riktige låter: ${user.rightSong}</p>
				<p>
				<c:forEach var="answer" items="${user.answers}">	
					Dag nummer ${answer.value.day}: ${answer.value.answerSong} ${answer.value.correctSong} - ${answer.value.answerArtist} - ${answer.value.correctArtist}<br /> 
				</c:forEach>
				</p>
			</c:forEach>
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