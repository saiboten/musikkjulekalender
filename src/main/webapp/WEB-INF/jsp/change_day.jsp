<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="before.jsp"%>
<script src="/static/script/admin.js" type="text/javascript"> </script>

<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title">Endre/Legg til dag!</h2>
			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
				<div><p>${feedback}</p></div>
				<form:form modelAttribute="day" method="post" action="/admin/day/change">
					Descr: <form:input path="description" size="80" value="${day.description}" /><br />
					<ul id="solution_artists">
					<c:forEach var="artist" items="${day.solutionsArtist}" varStatus="status">
						<li>Svar artist: ${status.index}: <form:input path="solutionsArtist[${status.index}]" value="${day.solutionsArtist[status.index]}" /></li>
						<script type="text/javascript">answersArtist++</script>
					</c:forEach>
					</ul>
					<input type="button" id="addArtist" value="Legg til artist" /><br />
					
					<ul id="solution_songs">
					<c:forEach var="song" items="${day.solutionsSong}" varStatus="status">
						<li>Svar song: ${status.index}: <form:input path="solutionsSong[${status.index}]" value="${day.solutionsSong[status.index]}" /></li>
						<script type="text/javascript">answersSong++</script>
					</c:forEach>
					</ul>
					<input type="button" id="addSong" value="Legg til sang" /><br />
					
					Videolenke:<form:input path="optionalSolutionVideo" value="${day.optionalSolutionVideo}" /><br />
					Lenke:<form:input path="link" size="80" value="${day.link}" /><br />
					revealdate (yyyy-MM-dd): <form:input path="revealDate" value="${revealDate}" /><br />
					solutiondate (yyyy-MM-dd): <form:input path="solutionDate" value="${solutionDate}" /><br />
					<input type="submit"></input>
					</form:form>
			</div>
		</div>

		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end #page -->

<%@ include file="after.jsp"%>
