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
				    <h3 class="panel-title">${day.realDate}. desember</h3>
				  </div>
				  <div class="panel-body">
		
							<p>${day.description}</p>
							<audio style="width: 15em" controls="">
								<source type="audio/mpeg" src="${day.link}"></source>
								<a href="${day.link}">Last ned låt</a>
							</audio>
								<c:if test="${statistics != null}">
										<p>
											<fmt:formatNumber type="percent"
												value="${statistics.dayMap[day.revealDateAsInt].correctArtist/numberOfUsers}" />
											av alle brukere gjettet rett artist<br />
											<fmt:formatNumber type="percent"
												value="${statistics.dayMap[day.revealDateAsInt].correctSong/numberOfUsers}" />
											av alle brukere gjettet rett låt
										</p>
								</c:if>
								
								<span title="Fasit" id="revealed_${day.revealDateAsInt}">
									${day.optionalSolutionVideo} "${day.solutionsSong[0]}" av
									"${day.solutionsArtist[0]}"
									</span>
								
								

					<c:choose>
						<c:when test='${loggedIn}'>
							<p>
								<c:if test="${answers[day.revealDateAsInt].answerSong != null}">
										Du tippet "<c:out value="${answers[day.revealDateAsInt].answerSong}"></c:out>" av
											"<c:out value="${answers[day.revealDateAsInt].answerArtist}"></c:out>".
	
											<br />

									<c:if test="${answers[day.revealDateAsInt].revealAnswer}">
											Du hadde
											<c:choose>
											<c:when test='${answers[day.revealDateAsInt].correctSong}'>
												<span class="green">rett låt<span class="glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></span>
											</c:when>
											<c:otherwise>
												<span class="red"> feil låt<span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span></span>
											</c:otherwise>
										</c:choose>

											og
											<c:choose>
											<c:when test='${answers[day.revealDateAsInt].correctArtist}'>
												<span class="green">rett artist</span>
											</c:when>
											<c:otherwise>
												<span class="red">feil artist</span>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:if>
							</p>
								<form role="form" action="/answer" method="POST">
									 <div class="form-group">
									    <label for="artistInput">Artist</label>
									    <input type="text" class="form-control" id="artistInput" placeholder="Artist" name="artist" value="${answers[day.revealDateAsInt].answerArtist}">
									  </div>
									  
									  <div class="form-group">
									    <label for="songInput">Sang</label>
									    <input type="text" class="form-control" id="songInput" placeholder="Sang" name="song" value="${answers[day.revealDateAsInt].answerSong}">
									  </div>
									  
									  <button type="submit" class="btn btn-default">Lagre forslag</button>
								</form>
						</c:when>
					</c:choose>
				  </div>
				</div>
				
	</div>
	</c:forEach>

<%@ include file="after.jsp"%>