
function append_elements() {
	
	$('#days').empty();
	
	$.getJSON("/days.json",function(data) {
		
		if(data.isLoggedIn) {
			$('#log-in-text').html('Hvis du ikke får opp oppgavene under, oppfrisk siden som en midlertidig workaround. Det jobbes med å finne en løsning på problemet. <a href="/m/login">Logg ut</a>');
		}
		else {
			$('#log-in-text').html('For å kunne besvare oppgave må du <a href="/m/login">logge inn</a>.');
		}
		
		$.each(data.days, function(index, day) {
			var liStart = '<li class="ui-li ui-li-static ui-body-b">';
			var headerAndDescription = '<h2>' + day.realDate +'. desember</h2><p>' + day.description +'</p>';
			var song = '<p id="audioplayer_' + day.revealDateAsInt +'"><a href="' + day.song + '">Dagens oppgave</a></p>';
			var solution = '';
			
			var now = new Date(data.date);
			var reveal_date = new Date(day.revealDate);
			var solution_date = new Date(day.solutionDate);
			
			if(now > solution_date) {
				var yourAnswerString = '';
				if(data.user.answers[day.revealDateAsInt] != undefined) {
					var answerArtist = data.user.answers[day.revealDateAsInt].correctSong ? "rett artist" : "feil artist";
					var answerSong = data.user.answers[day.revealDateAsInt].correctArtist ? "rett song" : "feil sang";
					yourAnswerString = "Du hadde " + answerArtist + " og " + answerSong; 
				}
				solution = '<p>Rett svar var: ' + day.solutionsArtist[0] + ' med ' + day.solutionsSong[0] + '. ' + yourAnswerString + '</p>';
			}
			
			var liEnd = '</li>';
			var form = '';
			if(data.isLoggedIn) {
				form = '<span id="input_form" />';
			}
			

			
			if(now < reveal_date) {
				$('#days').append(liStart + 'Luken er ikke åpnet' + liEnd);
			}
			else if(now > reveal_date && now < solution_date){
				$('#days').append(liStart + headerAndDescription + song + form + liEnd);
				if(data.isLoggedIn) {
					$('#input_form').load('/html/form.html',function() {
						if(data.user.answers[day.revealDateAsInt] != undefined) { //This puts the previously given input into the form
							$('#artistinput').attr('value',data.user.answers[day.revealDateAsInt].answerArtist)
							$('#songinput').attr('value',data.user.answers[day.revealDateAsInt].answerSong)
						}	
					});
				}
			}
			else {
				$('#days').append(liStart + headerAndDescription + song + solution + liEnd);
			}
			
			AudioPlayer.embed("audioplayer_" + day.revealDateAsInt, {
				soundFile : day.link
			});
		});
	});
}




$(document).ready(function() {
	
	$('#page1').bind('pageshow', function() {
		console.log('appending elements!');
		append_elements();
	});
	
	console.log('Im ready!');
	append_elements();
});