var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../../stores/DayStore');
var TopScores = require('./TopScores.jsx');

var TopScoreContainer = React.createClass({
    render() {
        return (
            <div>
                <h1>Toppscorelisten!</h1>
                <AltContainer store={DayStore}>
                    <TopScores />
                </AltContainer>
            </div>
        );
    }
});

module.exports = TopScoreContainer;
