var alt = require('../alt');
var AdminDayAction = require('../actions/AdminDayAction');

var request = require('superagent');
var debug = require('debug')('DayStore');

class AdminDayStore {
    constructor() {
        this.days = [];
        this.date = undefined;
        this.user = undefined;
        this.userResult = undefined;
        this.answers = undefined;
        this.topList = [];
        this.errorMessage = undefined;

        this.bindListeners({
            setData: AdminDayAction.SET_DATA,
            setError: AdminDayAction.UPDATE_DAYS_FAILED
        });
    }

    setData(data) {
        debug("Data: ", data);
        this.date = data.date;
        //this.days = data.days;
        this.user = data.user;
        this.userResult = data.userResult;
        this.topList = data.topList;
        this.answers = data.answers;

        this.days = data.days.map(function(day) {
            var returnDay = day;
            returnDay.solutions = [];
            data.solutions.forEach(function(solution){
                if(day.id == solution.day) {
                    returnDay.solutions.push(solution);
                }
            });
            return returnDay;
        });

        debug("Days :", this.days);
    }

    setError(error) {
        this.errorMessage = error;
    }
}

module.exports = alt.createStore(AdminDayStore, 'AdminDayStore');
