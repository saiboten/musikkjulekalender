<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<img src="/static/images/santas.jpg" class="img-responsive" alt="Responsive image">

<div class="row">
	<div class="col-md-4">
		<c:choose>
			<c:when test="${today != null}">
				<div>
					<h2 class="title">${today.realDate}.desember</h2>
					<div style="clear: both;">&nbsp;</div>

					<div class="entry">
						<h3>Musikkjulekalender 2014!</h3>
						<p>Velkommen til årets musikkjulekalender!</p>
					</div>

					<div class="entry">
						<h3>Dagens oppgave</h3>
						<p>${today.description}</p>
						<audio controls="">
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
									<form action="/answer" method="POST">
										<table>
											<tr>
												<td>Artist:</td>
												<td><input type="text" name="artist" /></td>
											<tr>
												<td>Sang:</td>
												<td><input type="text" name="song" /></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="submit" value="Lagre forslag" /></td>
											</tr>
										</table>
									</form>
								</c:if>
							</c:when>
						</c:choose>
					</div>
				</div>
			</c:when>
			<c:otherwise>

				<div>
					<h2 class="title">Julekalenderen!</h2>
					<div style="clear: both;">&nbsp;</div>

					<div class="entry">
						<h3>Musikkjulekalender!</h3>
						<p>Da blir det jaggu kalender i år og!</p>
					</div>

				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-4">
		<h2 class="col-md-6" class="title">Sosiale medier</h2>
		<p>
			<a href="https://twitter.com/share" class="twitter-share-button"
				data-text="Del" data-count="horizontal" data-via="saiboten">Tweet</a>
			<script type="text/javascript"
				src="//platform.twitter.com/widgets.js"></script>
		</p>
	</div>

	<div class="col-md-4">
		<c:if test="${bestUsers != null}">
			<h2 class="title">Topplisten</h2>

			<ul>
				<c:forEach var="bestUser" items="${bestUsers}">
					<li>${bestUser.totalScore}- ${bestUser.userNameNotMail}</li>
				</c:forEach>
			</ul>
		</c:if>

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
	<div class="col-md-4">
		<h2 class="title">Dagsvinnere!</h2>

		<div class="entry">

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



<%@ include file="after.jsp"%>