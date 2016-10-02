"use strict"
var alt = require('../alt');
var AdminDaySource = require('../sources/AdminDaySource');
var debug = require('debug')('AdminDayAction');

class AdminDayAction {
    getDays() {
        AdminDaySource.fetchDays()
            .then((data) => {
                debug("Got it baby! Got the songs: ", data);
                debug("This actions: ", this);
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

    updateDay(day) {
        debug("Updating day: ", day);
        AdminDaySource.updateDay(day).then((response) => {
            debug("Got some reply? ", response);
            this.actions.getDays();
        })
            .catch((errorMessage) => {
                debug("Error: ", errorMessage);
                this.actions.updateDaysFailed(errorMessage);
            });
    }

    addSolution(solutionObject) {
        AdminDaySource.addSolution(solutionObject)
            .then((response) => {
                this.actions.getDays();
            })
            .catch((errorMessage) => {
                debug("Error: ", errorMessage);
            })
    }

    updateDaysFailed(errorMessage) {
        debug("dispatching getDaysFailed");
        this.dispatch(errorMessage);
    }

    getDaysFailed(errorMessage) {
        debug("dispatching getDaysFailed");
        this.dispatch(errorMessage);
    }
}

debug("ALT:", AdminDayAction);
module.exports = alt.createActions(AdminDayAction);
