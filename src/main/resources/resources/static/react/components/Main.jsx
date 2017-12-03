
import React from 'react';
import { Facebook } from './facebook/Facebook';

var SingleGuessDayContainer = require('./days/day/SingleGuessDayContainer');
var UserStatisticsContainer = require('./userresults/UserStatisticsContainer');
var CurrentUserStatisticsContainer = require('./user/CurrentUserStatisticsContainer');

var HighScoreContainer = require('./highscore/HighScoreContainer');
var Days = require('./days/Days');
var Block = require('jsxstyle/Block');


var Main = React.createClass({

    render: function() {

       var imgStyle = { width: "100%" };
      var imgAttributes = {
        src: "/static/images/santas.jpg",
        alt: "Julenisse"
      }

        return (
            <Block className="main-wrapper" backgroundColor="white" borderRadius="5px" margin="0 auto">
                <Block>
                    <Block padding-top="10px" component="h1" textAlign="center">Musikkjulekalender 2017!</Block>
                    <Block width="100%" component="img" props={imgAttributes} />
                </Block>
                <Block display="flex" flexWrap="wrap" padding="15px">
                    <SingleGuessDayContainer />
                    <Facebook />
                </Block>
                <UserStatisticsContainer />
                <HighScoreContainer />
                <CurrentUserStatisticsContainer />
                  <p className="smallspace">Følg oss gjerne på <a href="https://www.facebook.com/musikkjulekalender">facebook!</a></p>
                <Days />
        </Block>)
    }
});

module.exports = Main;
