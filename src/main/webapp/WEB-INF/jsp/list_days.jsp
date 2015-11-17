<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="before.jsp"%>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">Admin</h3>
			  </div>
			  <div class="panel-body">
				<ul>
				<c:forEach var="day" items="${days}">
					<li>
						<p><a href="/admin/day/change/${day.revealDateAsInt}">${day.revealDateAsInt} - ${day.description}</a></p>
						<p><a id="deletelink" href="/admin/day/delete/${day.revealDateAsInt}">Slett dag</a></p>
						<p><a href="/admin/updateScores/${day.revealDateAsInt}">Oppdater dag</a></p>
						<p><a href="/admin/newwinner/${day.revealDateAsInt}">Kår vinner</a></p>
					</li>
				</c:forEach>
				</ul>
				<p><a href="/admin/users">Se brukere</a></p>
				<p><a href="/admin/day/add">Legg til dag</a></p>
				<p><a href="/admin/overview">Oversikt</a></p>
			  </div>
			</div>
		</div>
		</div>

<script type="text/javascript">
$('#deletelink').click(function() {
	
});
</script>

<!-- end #page -->

<%@ include file="after.jsp"%>
