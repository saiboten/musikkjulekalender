<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="before.jsp"%>

<div class="col-md-12 main">
	<h1 style="text-align: center;">Musikkjulekalender 2015!</h1>
	<div class="headerImageDiv"><img src="/static/images/santas.jpg" class="img-responsive headerImage" alt="Responsive image"></div>
</div>

	<c:choose>
		<c:when test="${today != null}">
				<div class="col-md-12 main" id="frontpage"></div>
		</c:when>
		<c:otherwise>
			<div class="col-md-12">
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">Musikkjulekalender 2015!</h3>
				  </div>
				  <div class="panel-body">
					<p>Coming very soon!</p>
				  </div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>



<%@ include file="after.jsp"%>
