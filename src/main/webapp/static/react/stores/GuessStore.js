var alt = require('../alt');
var GuessAction = require('../actions/GuessAction');
var request = require('superagent');
var debug = require('debug')('GuessStore');

class GuessStore {
    constructor() {
        this.guess = "";

        this.bindListeners({
            guessDone: GuessAction.GUESS_DONE
        });
    }

    guessDone(guess) {
        debug("Guess: ", guess);
        this.guess = guess;
    }
}

module.exports = alt.createStore(GuessStore, 'GuessStore');
