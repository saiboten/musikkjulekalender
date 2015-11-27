"use strict"
var alt = require('../alt');
var GuessSource = require('../sources/GuessSource');
var debug = require('debug')('SongActions');

class GuessAction {
    guess(song) {
        // we dispatch an event here so we can have "loading" state.
        this.dispatch();
        GuessSource.guess(song)
            .then((response) => {
                debug("Got it baby! Got the songs: ", response);
                this.actions.guessDone(response);
            })
            .catch((errorMessage) => {
                this.actions.guessFailed(errorMessage);
            });
    }

    guessDone(guessResult) {
        debug("Guess done! Guess result: ", guessResult);
        this.dispatch(guessResult);
    }

    guessFailed(errorMessage) {
      debug("Something failed when guessing: ", errorMessage);
      this.dispatch(errorMessage);
    }

}

module.exports = alt.createActions(GuessAction);
