/**
 * 
 */
var answersArtist = 0;
var answersSong = 0;

$(document).ready(function() {
	$('#addArtist').click(function() {
		$('#solution_artists').append('<li>Svar artist: ' + answersArtist + ': <input type="text" name="solutionsArtist[' + answersArtist + ']" id="solutionsArtist' + answersArtist +'" /></li>');
		answersArtist++;					
	});
	
	$('#addSong').click(function() {
		$('#solution_songs').append('<li>Svar song: ' + answersSong + ': <input type="text" name="solutionsSong[' + answersSong + ']" id="solutionsSong' + answersSong +'" /></li>');
		answersSong++;
	});
});