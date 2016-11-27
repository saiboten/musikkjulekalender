var React = require('react');
var AltContainer = require('alt-container');
var CurrentUserStatistics = require('./CurrentUserStatistics.jsx');
var DayStore = require('../../stores/DayStore');

var CurrentUserStatisticsContainer = React.createClass({
    render() {
        return (
          <div>
            <h1>Dine resultater</h1>
          <AltContainer store={DayStore}>
                 <CurrentUserStatistics />
          </AltContainer>
        </div>

        );
    }
});

module.exports = CurrentUserStatisticsContainer;
