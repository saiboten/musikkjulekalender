<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="before.jsp"%>

<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title">I alle dager!</h2>
			<div style="clear: both;">&nbsp;</div>
			<div class="entry">
				<ul>
				<c:forEach var="day" items="${days}">
					<li>
						<p><a href="/admin/day/change/${day.revealDateAsInt}">${day.revealDateAsInt} - ${day.description}</a></p>
						<p><a id="deletelink" href="/admin/day/delete/${day.revealDateAsInt}">Slett dag</a></p>
						<p><a href="/admin/updateScores/${day.revealDateAsInt}">Oppdater dag</a></p>
					</li>
				</c:forEach>
				</ul>
				<p><a href="/admin/users">Se brukere</a></p>
				<p><a href="/admin/day/add">Legg til dag</a></p>
				<p><a href="/admin/overview">Overview</a></p>
				
			</div>
		</div>

		<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>

<script type="text/javascript">
$('#deletelink').click(function() {
	
});
</script>

<!-- end #page -->

<%@ include file="after.jsp"%>
