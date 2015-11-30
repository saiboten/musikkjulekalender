<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="before_main.jsp"%>
<div class="col-md-12 main">
  <h1 style="text-align: center;">Musikkjulekalender 2015!</h1>
  <div class="headerImageDiv">
    <img src="/static/images/santas.jpg" class="img-responsive headerImage" alt="Responsive image">
    </img>
  </div>
</div>
<div class="col-md-12 main">
  <div class="col-md-6 pane" id="frontpage">
  </div>
  <div class="col-md-6 pane">
    <h1>Din score</h1>
    <div id="currentuserstatistics">
    </div>
  </div>
</div>
<div class="col-md-12 main">
  <div class="col-md-6 pane">
    <h1>Dagens beste!</h1>
    <div id="userstatistics">
    </div>
  </div>
  <div class="col-md-6 pane">
    <h1>Toppscorelisten!</h1>
    <div id="topscore">
    </div>
  </div>
</div>
<div class="col-md-12 main">
  <h1>Løsninger</h1>
  <div id="MusikkJulekalender">
  </div>
</div>
</div>
<%@ include file="after.jsp"%>
