"use strict"
var alt = require('../alt');
var DaySource = require('../sources/DaySource');
var debug = require('debug')('SongActions');

class DayAction {
    getDays() {
        // we dispatch an event here so we can have "loading" state.
        this.dispatch();
        DaySource.fetchDays()
            .then((data) => {
                debug("Got it baby! Got the songs: ", data);
                this.actions.setData(data);
            })
            .catch((errorMessage) => {
                this.actions.getDaysFailed(errorMessage);
            });
    }

    setData(data) {
        debug("Calling set data action. Data: ", data);
        this.dispatch(data);
    }

    getDaysFailed(errorMessage) {
      this.dispatch(errorMessage);
    }
}

module.exports = alt.createActions(DayAction);
