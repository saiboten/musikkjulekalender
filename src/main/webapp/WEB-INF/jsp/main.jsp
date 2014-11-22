<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div id="page">
	<div id="content">

		<c:choose>
			<c:when test="${today != null}">
				<div class="post">
					<h2 class="title">${today.realDate}. desember</h2>
					<div style="clear: both;">&nbsp;</div>
					
					<div class="entry">
						<h3>Musikkjulekalender 2013!</h3>
						<p>Gratulerer til Terje og co, som vant musikkjulekalenderen i år 2013 med minst mulig margin! Godt jobbet! Krus og diplom kommer i posten!</p>
					</div>

					<div class="entry">
						<h3>Dagens oppgave</h3>
						<p>${today.description}</p>
						<p id="audioplayer_1">
							Link: <a href="${today.link}">Last ned</a>
						</p>
						<script type="text/javascript">
							AudioPlayer.embed("audioplayer_1", {
								soundFile : "${today.link}"
							});
						</script>

						<c:choose>
							<c:when test="${loggedIn}">
							<c:if test="${now > today.revealDate.time && now < today.solutionDate.time }">
									<c:if test="${user.answers[today.revealDateAsInt].answerSong != null}">
										<p>Du tippet ${user.answers[today.revealDateAsInt].answerSong} av
											${user.answers[today.revealDateAsInt].answerArtist}</p>
									</c:if>
									<form action="/answer" method="POST">
										<table>
											<tr>
												<td>Artist:</td>
												<td><input type="text" name="artist" />
												</td>
											<tr>
												<td>Sang:</td>
												<td><input type="text" name="song" /></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="submit" value="Lagre forslag" />
												</td>
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

				<div class="post">
					<h2 class="title">Julekalenderen!</h2>
					<div style="clear: both;">&nbsp;</div>

					<div class="entry">
						<h3>Musikkjulekalender!</h3>
						<p>Da blir det jaggu kalender i år og!</p>
					</div>

				</div>
			</c:otherwise>
		</c:choose>

		<div style="clear: both;">&nbsp;</div>
	</div>
	
	
	<div id="sidebar">
	
	<div id="social"><h2 class="title">Sosiale medier</h2>
			<p>
			<iframe
				src="http://www.facebook.com/plugins/like.php?href=http://musikkjulekalender2013.appspot.com"
				scrolling="no" frameborder="0"
				style="border: 1px dotted black; width: 250px; margin-top: 10px; height: 35px;"></iframe>

			<a href="https://twitter.com/share" class="twitter-share-button"
				data-text="Del" data-count="horizontal" data-via="saiboten">Tweet</a>
			<script type="text/javascript"
				src="//platform.twitter.com/widgets.js"></script>
		</p>
	</div>
	
	<div class="splashText">
		<c:if test="${bestUsers != null}">
			<h2 class="title">Topplisten</h2>

			<ul>
				<c:forEach var="bestUser" items="${bestUsers}">
					<li>${bestUser.totalScore} - ${bestUser.userNameNotMail}</li>
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
	
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>
	
</div>

<!-- end #page -->



<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title">Dagsvinnere!</h2>

			<div class="entry">
			
				<c:choose>
					<c:when test="${not empty longDays}">
						<table>
							<c:forEach var="longDay" items="${longDays}">
									<tr>
									<td>Dag ${days[longDay].realDate}: <c:out value="${winners[longDay].userNameNotMail}" /></td>
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
	<!-- end #content -->
	<div style="clear: both;">&nbsp;</div>
</div>



<%@ include file="after.jsp"%>