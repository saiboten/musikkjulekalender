var React = require('react');
var AltContainer = require('alt-container');
var CurrentUserStatistics = require('./CurrentUserStatistics.jsx');
var DayStore = require('../../stores/DayStore');

var CurrentUserStatisticsContainer = React.createClass({
    render() {
        return (
          <AltContainer store={DayStore}>
                 <CurrentUserStatistics />
          </AltContainer>
        );
    }
});

module.exports = CurrentUserStatisticsContainer;
