<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>
		<c:forEach var="day" items="${days}" varStatus="rowCounter">
		
		<script>
				$(document).ready(function() {
					$("#revealer_${day.revealDateAsInt}").click(function() {
						$('#revealed_${day.revealDateAsInt}').dialog({
							model: true,
							width: "auto",
							 buttons: {
					         "Lukk": function() {
					         	$( this ).dialog( "close" );
					         }}
						});
					});
				});
			</script>
		
			<div class="col-md-6">
			
				
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">${day.revealDate}</h3>
				  </div>
				  <div class="panel-body">
		
							<p>${day.description}</p>
							<audio preload="none" style="width: 15em" controls="">
								<source type="audio/mpeg" src="${day.link}"></source>
								<a href="${day.link}">Last ned låt</a>
							</audio>
								
								<span title="Fasit" id="revealed_${day.revealDateAsInt}">
									${day.optionalSolutionVideo} "${day.solutionsSong[0]}" av
									"${day.solutionArtist}"
									</span>
								
								<c:forEach var="user" items="${users}">
									<c:if test="${user.answers[day.revealDateAsInt] != null}">
										<p><b>${user.userName}</b>: ${user.answers[day.revealDateAsInt].answerSong} (${user.answers[day.revealDateAsInt].correctSong})</p>
									</c:if>
								</c:forEach>
				  </div>
				</div>
				
	</div>
	</c:forEach>

<%@ include file="after.jsp"%>