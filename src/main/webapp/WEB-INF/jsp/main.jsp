<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<p><img src="/static/images/santas.jpg" class="img-responsive" alt="Responsive image"></p>

<div class="row">
	<div class="col-md-6">
		<c:choose>
			<c:when test="${today != null}">
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Musikkjulekalender 2015!</h3>
					  </div>
					  <div class="panel-body">
						<p>Coming soon!</p>
					  </div>
					</div>
					
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Dagens oppgave: ${today.realDate}.desember</h3>
					  </div>
					  <div class="panel-body">
					   <p>${today.description}</p>
						<audio style="width: 15em" class="media" controls="">
							<source type="audio/mpeg" src="${today.link}"></source>
							<a href="${today.link}">Last ned låt</a>
						</audio>

						<c:choose>
							<c:when test="${loggedIn}">
								<c:if
									test="${now > today.revealDate.time && now < today.solutionDate.time }">
									<c:if
										test="${user.answers[today.revealDateAsInt].answerSong != null}">
										<p>Du tippet
											${user.answers[today.revealDateAsInt].answerSong} av
											${user.answers[today.revealDateAsInt].answerArtist}</p>
									</c:if>
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
								</c:if>
							</c:when>
						</c:choose>
					  </div>
					</div>
			</c:when>
			<c:otherwise>

					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">Musikkjulekalender 2015!</h3>
					  </div>
					  <div class="panel-body">
						<p>Coming very soon!</p>
					  </div>
					</div>

			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-6">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Score</h3>
		  </div>
		  <div class="panel-body">
		  <ul>
		  	<h4>Toppscore</h4>
			  <c:forEach var="bestUser" items="${bestUsers}">
					<li>${bestUser.totalScore} poeng: ${bestUser.userNameNotMail}</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</div>
	
	<div class="col-md-6">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Statistikk</h3>
		  </div>
		  <div class="panel-body">
			  <c:if test="${userStats != null}">
				<c:if test="${user.daysCalculated != 0}">
					<h2 class="title">Statistikk</h2>
					<p>
						Du har ${user.rightArtist} av ${user.daysCalculated} riktig
						artister/band.<br /> Du har ${user.rightSong} av
						${user.daysCalculated} riktige låter.<br /> Total poengsum:
						${user.totalScore}
					</p>
				</c:if>
			</c:if>
		</div>
	</div>
	</div>
	
		
	
	<div class="col-md-6">
	
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title">Dagsvinnere</h3>
		  </div>
		  <div class="panel-body">
		   <c:choose>
				<c:when test="${not empty longDays}">
					<table>
						<c:forEach var="longDay" items="${longDays}">
							<tr>
								<td>Dag ${days[longDay].realDate}: <c:out
										value="${winners[longDay].userNameNotMail}" /></td>
							</tr>
						</c:forEach>

					</table>
				</c:when>
				<c:otherwise>
					<p>Ingen vinnere enda.</p>
				</c:otherwise>
			</c:choose>
		  </div>
		</div>

		

	</div>
	
</div>



<%@ include file="after.jsp"%>