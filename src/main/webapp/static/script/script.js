
$(document).ready(function() {
	$('#save').submit(function(e) {
		e.preventDefault();
		e.stopPropagation();
		
		var song = $('#songInput').val();
		console.log("Song: ", song);
		
		var success = function(data) {
			console.log("Success! Data: ", data);
		}
		
		$.ajax({
			  type: "POST",
			  url: "answer?song="+song,
			  dataType: "json"
			}).done(function(data) {
				
				console.log(data);
			
				$('#answerFeedback').html(data.feedback);
				
				if(data.correct) {
					$('#answerSpan').hide();
				}
				
			}).fail(function(err) {
				console.log("Fail!");
			}).always(function() {
				console.log("Always!");
			})
	})
});