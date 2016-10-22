"use strict"
var alt = require('../alt');
var GuessSource = require('../sources/GuessSource');
var debug = require('debug')('GuessAction');

class GuessAction {
    guess(song) {
        // we dispatch an event here so we can have "loading" state.
        GuessSource.guess(song)
            .then((response) => {
                debug("Guess complete, response: ", response);
                this.actions.guessDone(response);
            })
            .catch((errorMessage) => {
                this.actions.guessFailed(errorMessage);
            });
    }

    guessDone(guessResult) {
        debug("dispatching guessDone: Guess done! Guess result: ", guessResult);
        this.dispatch(guessResult);
    }

    guessFailed(errorMessage) {
      debug("dispatching guessFailed: Something failed when guessing: ", errorMessage);
      this.dispatch(errorMessage);
    }

}

module.exports = alt.createActions(GuessAction);
