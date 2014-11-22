<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Musikkjulekalenderen 2013</title>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
<script src="/script/christmas-mobile.js"></script>
<script type="text/javascript" src="/audio-player.js"></script>
<script type="text/javascript">
	AudioPlayer.setup("../player.swf", {
		width : 290
	});
</script>
</head>
<body>
<!-- Home -->
<div data-role="page" id="page1">
    <div id="header" data-theme="a" data-role="header">
        <h3>
            Musikkjulekalenderen
        </h3>
    </div>
    <div data-role="content">
        <h5 id="infotext">
            Velkommen til musikkjulekalderen 2013! Her er dagens oppgave
        </h5>
        <form id="submitAnswer" action="/answer" method="POST" data-ajax="false">
            <div data-role="fieldcontain">
                <fieldset data-role="controlgroup">
                    <label for="textinput1">
                        Artist
                    </label>
                    <input name="artist" id="textinput1" placeholder="Velg låt her" value=""
                    type="text">
                </fieldset>
            </div>
            <div data-role="fieldcontain">
                <fieldset data-role="controlgroup">
                    <label for="textinput2">
                        Sang
                    </label>
					<input name="song" id="textinput2" placeholder="" value="" type="text">
                </fieldset>
            </div>
            <input type="submit" value="Submit">
        </form>
        <div>
            <a id="linkToOverview" href="/m/overview" data-transition="fade">
                Se alle oppgaver
            </a>
        </div>
    </div>
</div>
 
</body>
</html>