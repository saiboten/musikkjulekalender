<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div data-role="page" id="page1">
    <div id="header" data-theme="a" data-role="header">
			<c:choose>
				<c:when test="${loggedIn}">
					<h2 class="title"><a href="${logoutUrl}">Logg ut</a></h2>
				</c:when>
				<c:otherwise>
					<h2 class="title"><a href="${loginUrl}">Logg inn</a></h2>
				</c:otherwise>
			</c:choose>
		<p><a href="/m">Tilbake til forsiden</a></p>
    </div>
</div>