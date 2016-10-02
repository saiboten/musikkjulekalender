"use strict"
var alt = require('../alt');
var DaySource = require('../sources/DaySource');
var debug = require('debug')('DayAction');


class DayAction {
    getDays() {
        DaySource.fetchDays()
            .then((data) => {
                debug("Got it baby! Got the songs: ", data);
                this.actions.setData(data);
            })
            .catch((errorMessage) => {
                debug("Error: ", errorMessage);
                this.actions.getDaysFailed(errorMessage);
            });
    }

    setData(data) {
        debug("dispatching setData: Calling set data action. Data: ", data);
        this.dispatch(data);
    }

    getDaysFailed(errorMessage) {
        debug("dispatching getDaysFailed");
      this.dispatch(errorMessage);
    }
}

debug("ALT:", DayAction);

module.exports = alt.createActions(DayAction);
