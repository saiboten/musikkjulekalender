var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../../stores/DayStore');
var HighScoreList = require('./HighScoreList.jsx');

var HighScoreContainer = React.createClass({
    render() {
        return (
            <div>
                <h1>Toppscorelisten!</h1>
                <AltContainer store={DayStore}>
                    <HighScoreList />
                </AltContainer>
            </div>
        );
    }
});

module.exports = HighScoreContainer;
