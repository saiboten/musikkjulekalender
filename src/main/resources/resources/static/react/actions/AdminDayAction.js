"use strict"
var alt = require('../alt');
var AdminDaySource = require('../sources/AdminDaySource');
var debug = require('debug')('AdminDayAction');

class AdminDayAction {
    getDays() {
        AdminDaySource.fetchDays()
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

module.exports = alt.createActions(AdminDayAction);
