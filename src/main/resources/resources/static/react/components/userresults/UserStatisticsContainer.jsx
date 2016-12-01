var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../../stores/DayStore');
var UserStatistics = require('./UserStatistics');

var UserStatisticsContainer = React.createClass({
    render() {
        return (
            <div>
                <h1>Dagens beste!</h1>
                <p className="smallspace">Dette viser klokkeslettet oppgaven ble løst på per bruker</p>
                <AltContainer store={DayStore}>
                     <UserStatistics />
              </AltContainer>
            </div>
        );
    }
});

module.exports = UserStatisticsContainer;
