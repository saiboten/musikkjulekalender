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
                this.actions.updateDays(data.days);
                this.actions.setDate(data.date);
                this.actions.setUser(data.user);
            })
            .catch((errorMessage) => {
                this.actions.getDaysFailed(errorMessage);
            });
    }

    updateDays(days) {
        debug("Calling update days action. Days: ", days);
        this.dispatch(days);
    }
    setDate(date) {
        debug("Calling set date action. Date: ", date);
        this.dispatch(date);
    }

    getDaysFailed(errorMessage) {
      this.dispatch(errorMessage);
    }

    setUser(user) {
      this.dispatch(user);
    }


}

module.exports = alt.createActions(DayAction);
