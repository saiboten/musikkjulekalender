import { Block } from 'jsxstyle';
import React from 'react';
import { Facebook } from './facebook/Facebook';

var SingleGuessDayContainer = require('./days/day/SingleGuessDayContainer');
var UserStatisticsContainer = require('./userresults/UserStatisticsContainer');
var CurrentUserStatisticsContainer = require('./user/CurrentUserStatisticsContainer');

var HighScoreContainer = require('./highscore/HighScoreContainer');
var Days = require('./days/Days');


var Main = React.createClass({

    render: function () {

        var imgStyle = { width: "100%" };
        var imgAttributes = {
            src: "/static/images/santas.png",
            alt: "Julenisse"
        }

        return (
            <Block className="main-wrapper" backgroundColor="white" borderRadius="5px" margin="0 auto">
                <Block
                    mediaQueries={{
                        sm: 'screen and (max-width: 450px)',
                    }}
                    display="flex"
                    flexDirection="column"
                    smFlexDirection="row"
                    smJustifyContent="space-between"
                    smAlignItems="center"
                    smPadding="5px"
                >
                    <Block component="h1" textAlign="center">Musikkjulekalender!</Block>
                    <Block
                        mediaQueries={{
                            sm: 'screen and (max-width: 450px)',
                        }}
                        height="300px"
                        width="232px"
                        margin="0 auto"
                        smWidth="50px"
                        smHeight="50px"
                        smMarginRight="10px"
                        smBorderRadius="50%"
                        component="img"
                        props={imgAttributes} />
                </Block>
                <Block display="flex" flexWrap="wrap" padding="5px">
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
