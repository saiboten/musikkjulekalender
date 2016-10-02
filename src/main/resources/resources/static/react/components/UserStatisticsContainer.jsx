var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var UserStatistics = require('./UserStatistics.jsx');

var UserStatisticsContainer = React.createClass({
    render() {
        return (
          <AltContainer store={DayStore}>
                 <UserStatistics />
          </AltContainer>
        );
    }
});

module.exports = UserStatisticsContainer;
