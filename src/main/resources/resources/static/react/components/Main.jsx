var React = require('react');
var SingleGuessDayContainer = require('./SingleGuessDayContainer.jsx');
var UserStatisticsContainer = require('./UserStatisticsContainer.jsx');
var TopScoreContainer = require('./topScore/TopScoreContainer.jsx');
var DaysContainer = require('./DaysContainer.jsx');
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
                <TopScoreContainer />
                <DaysContainer />
        </Block>)
    }
});

module.exports = Main;