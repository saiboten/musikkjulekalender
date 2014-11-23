<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div id="page">
	<div id="fullcontent">

		<div class="post">
			<h2 class="title">
				<a href="/overview#today">Gå til dagens luke</a>
			</h2>
			<div style="clear: both;">&nbsp;</div>
		</div>

		<c:forEach var="day" items="${days}" varStatus="rowCounter">

			<script>
				$(document).ready(function() {
					$("#revealer_${day.revealDateAsInt}").click(function() {
						$('#revealed_${day.revealDateAsInt}').dialog({
							width : 460,
							model: true,
							 buttons: {
					         "Lukk": function() {
					         	$( this ).dialog( "close" );
					         }}
						});
					});
				});
			</script>


			<c:choose>
				<c:when test="${rowCounter.count % 2 == 0}">
					<div class="right">
				</c:when>
				<c:otherwise>
					<div class="left">
				</c:otherwise>
			</c:choose>


			<div class="post">

				<c:if test="${now > day.revealDate.time && now < day.solutionDate.time}">
					<div id="today"></div>
				</c:if>
				<h2 class="title">${day.realDate}. desember</h2>
				<div style="clear: both;">&nbsp;</div>
				<div class="calendar">
					<c:choose>
						<c:when test='${now > day.revealDate.time}'>
							<p>${day.description}</p>
							<p id="audioplayer_${day.revealDateAsInt}">
								<a href="${day.link}">Last ned spor</a>
							</p>
							<audio controls="">
								<source type="audio/mpeg" src="${today.link}"></source>
								<a href="${today.link}">Last ned låt</a>
							</audio>
							<c:if test='${now > day.solutionDate.time}'> 
								<c:if test="${statistics != null}">
									<c:if test="${now > day.solutionDate.time}">
										<p>
											<fmt:formatNumber type="percent"
												value="${statistics.dayMap[day.revealDateAsInt].correctArtist/numberOfUsers}" />
											av alle brukere gjettet rett artist<br />
											<fmt:formatNumber type="percent"
												value="${statistics.dayMap[day.revealDateAsInt].correctSong/numberOfUsers}" />
											av alle brukere gjettet rett låt
										</p>
									</c:if>
								</c:if>
							</c:if>

							<c:if test='${now > day.solutionDate.time}'>
								<c:choose>
									<c:when test="${loggedIn}">
										<c:choose>
											<c:when test="${answers[day.revealDateAsInt].answerSong != null}">
											
												<p>
													Riktig svar var: "${day.solutionsSong[0]}" av
													"${day.solutionsArtist[0]}"
													<c:if test="${day.optionalSolutionVideo != null}">
														<br />
														<input type="button" id="revealer_${day.revealDateAsInt}"
															value="Se video">
														<span class="hideme" title="Video"
															id="revealed_${day.revealDateAsInt}">
															${day.optionalSolutionVideo}</span>
												</p>
							</c:if>
							</p>
						</c:when>
						<c:otherwise>
							<p>
								<input type="button" id="revealer_${day.revealDateAsInt}" value="Fasit"><span
									class="hideme" title="Fasit" id="revealed_${day.revealDateAsInt}">
									${day.optionalSolutionVideo} "${day.solutionsSong[0]}" av
									"${day.solutionsArtist[0]}"
							</p>
							</span>
							</p>
						</c:otherwise>
					</c:choose>
					</c:when>
					<c:otherwise>

						<p>
							<input type="button" id="revealer_${day.revealDateAsInt}" value="Fasit"><span
								class="hideme" title="Fasit" id="revealed_${day.revealDateAsInt}">
								${day.optionalSolutionVideo} "${day.solutionsSong[0]}" av
								"${day.solutionsArtist[0]}"
						</p>
						</span>
						</p>

					</c:otherwise>
					</c:choose>
					</c:if>

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
												<span class="green">rett låt</span>
											</c:when>
											<c:otherwise>
												<span class="red"> feil låt</span>
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
							<c:if test="${now > day.revealDate.time && now < day.solutionDate.time }">
								<form action="/answer" method="POST">
									<table>
										<tr>
											<td>Artist:</td>
											<td><input type="text" name="artist" value="${answers[day.revealDateAsInt].answerArtist}" />
											</td>
										<tr>
											<td>Sang:</td>
											<td><input type="text" name="song" value="${answers[day.revealDateAsInt].answerSong}" />
											</td>
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
					</c:when>
					<c:otherwise>
						<p>Luke ikke åpnet.</p>
					</c:otherwise>
					</c:choose>
				</div>


				<!-- 				<div style="clear: both;">&nbsp;</div> -->
			</div>
	</div>
	</c:forEach>
</div>
<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
</div>
<!-- end #page -->

<%@ include file="after.jsp"%>