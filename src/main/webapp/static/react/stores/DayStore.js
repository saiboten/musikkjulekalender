var alt = require('../alt');
var DayActions = require('../actions/DayAction');
var request = require('superagent');
var debug = require('debug')('DayStore');

class DayStore {
    constructor() {
        this.days = [];
        this.date = undefined;
        this.user = undefined;

        this.bindListeners({
            addDays: DayActions.UPDATE_DAYS,
            setDate: DayActions.SET_DATE,
            setUser: DayActions.SET_USER
        });
    }

    addDays(days) {
        debug("Days: ", days);
        this.days = days;
    }

    setDate(date) {
        debug("Date: ", date);
        this.date = date;
    }

    setUser(user) {
        debug("User: ", user);
        this.user = user;
    }
}

module.exports = alt.createStore(DayStore, 'DayStore');
