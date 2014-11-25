<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div id="page">
	<div id="fullcontent">

		<div class="page-header">
			<h2 class="title">
				<a href="/overview#today">Gå til dagens luke</a>
			</h2>
		</div>

		<c:forEach var="day" items="${days}" varStatus="rowCounter">
			<div class="col-md-6">
				<c:if test="${now > day.revealDate.time && now < day.solutionDate.time}">
					<div id="today"></div>
				</c:if>
				
				
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">${day.realDate}. desember</h3>
				  </div>
				  <div class="panel-body">
					<c:choose>
						<c:when test='${now > day.revealDate.time}'>
							<p>${day.description}</p>
							<audio style="width: 15em" controls="">
								<source type="audio/mpeg" src="${day.link}"></source>
								<a href="${day.link}">Last ned låt</a>
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
								<input type="button" id="revealer_${day.revealDateAsInt}" value="Fasit">
								<span title="Fasit" id="revealed_${day.revealDateAsInt}">
									${day.optionalSolutionVideo} "${day.solutionsSong[0]}" av
									"${day.solutionsArtist[0]}"
									</span>
							</p>
							
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
					</c:when>
					<c:otherwise>
						<p>Luke ikke åpnet.</p>
					</c:otherwise>
					</c:choose>
				  </div>
				</div>
				
	</div>
	</c:forEach>
</div>


<!-- end #content -->
<div style="clear: both;">&nbsp;</div>
</div>
<!-- end #page -->

<%@ include file="after.jsp"%>