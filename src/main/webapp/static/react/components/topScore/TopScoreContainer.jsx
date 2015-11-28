var React = require('react');
var AltContainer = require('alt/AltContainer');
var DayStore = require('../../stores/DayStore');
var TopScores = require('./TopScores.jsx');

var TopScoreContainer = React.createClass({
    render() {
        return (
          <AltContainer store={DayStore}>
                 <TopScores />
          </AltContainer>
        );
    }
});

module.exports = TopScoreContainer;
