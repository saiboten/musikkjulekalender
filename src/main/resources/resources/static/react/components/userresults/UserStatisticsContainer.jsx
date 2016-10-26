var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../../stores/DayStore');
var DayActions = require('../../actions/DayAction');
var UserStatistics = require('./UserStatistics');

var UserStatisticsContainer = React.createClass({
    render() {
        return (
            <div>
                <h1>Dagens beste!</h1>
                <AltContainer store={DayStore}>
                     <UserStatistics />
              </AltContainer>
            </div>
        );
    }
});

module.exports = UserStatisticsContainer;
