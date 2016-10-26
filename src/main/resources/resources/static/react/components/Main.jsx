var React = require('react');
var SingleGuessDayContainer = require('./days/day/SingleGuessDayContainer');
var UserStatisticsContainer = require('./userresults/UserStatisticsContainer');
var HighScoreContainer = require('./highscore/HighScoreContainer');
var Days = require('./days/Days');
var Block = require('jsxstyle/Block');

var Main = React.createClass({

    render: function() {

       var imgStyle = { width: "100%" };

        return (
            <Block className="main-wrapper" backgroundColor="white" borderRadius="5px" margin="0 auto">
                <Block>
                    <Block component="h1" textAlign="center">Musikkjulekalender 2016!</Block>
                    <img style={imgStyle} src="/static/images/santas.jpg" alt="Responsive image" />
                </Block>
                <SingleGuessDayContainer />
                <UserStatisticsContainer />
                <HighScoreContainer />
                <Days />
        </Block>)
    }
});

module.exports = Main;
