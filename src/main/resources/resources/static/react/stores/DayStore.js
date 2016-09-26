var alt = require('../alt');
var DayActions = require('../actions/DayAction');
var request = require('superagent');
var debug = require('debug')('DayStore');

class DayStore {
    constructor() {
        this.days = [];
        this.date = undefined;
        this.user = undefined;
        this.userResult = undefined;
        this.answers = undefined;
        this.topList = [];

        this.bindListeners({
            setData: DayActions.SET_DATA
        });
    }

    setData(data) {
        debug("Data: ", data);
        this.date = data.date;
        this.days = data.days;
        this.user = data.user;
        this.userResult = data.userResult;
        this.topList = data.topList;
        this.answers = data.answers;
    }
}

module.exports = alt.createStore(DayStore, 'DayStore');
