var React = require('react');
var SingleGuessDayContainer = require('./SingleGuessDayContainer.jsx');
var UserStatisticsContainer = require('./UserStatisticsContainer.jsx');
var TopScoreContainer = require('./topScore/TopScoreContainer.jsx');
var DaysContainer = require('./DaysContainer.jsx');
var Block = require('jsxstyle/Block');

var Main = React.createClass({


    render: function() {

       var imgStyle = { margin: "0 auto" };

        return (
            <Block backgroundColor="white" borderRadius="5px" width="980px" margin="0 auto">
                <Block>
                    <h1>Musikkjulekalender 2016!</h1>
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