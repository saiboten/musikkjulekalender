var React = require('react');
var ReactDOM = require('react-dom');
var debug = require('debug')('App');

var DaysContainer = require('./components/DaysContainer.jsx');
var SingleGuessDayContainer = require('./components/SingleGuessDayContainer.jsx');
var UserStatisticsContainer = require('./components/UserStatisticsContainer.jsx');
var CurrentUserStatisticsContainer = require('./components/user/CurrentUserStatisticsContainer.jsx');
var TopScoreContainer = require('./components/topScore/TopScoreContainer.jsx');
var AdminOverviewContainer = require('./components/admin/AdminOverviewContainer.jsx');
var DayActions = require('./actions/DayAction');

if(document.getElementById('DaysContainer')) {
    ReactDOM.render(
        <DaysContainer />,
        document.getElementById('DaysContainer')
    );
}

if(document.getElementById('frontpage')) {
    ReactDOM.render(
        <SingleGuessDayContainer />,
        document.getElementById('frontpage')
    );
}

if(document.getElementById('admin')) {
    ReactDOM.render(
        <AdminOverviewContainer />,
        document.getElementById('admin')
    );
}

if(document.getElementById('userstatistics')) {
    ReactDOM.render(
        <UserStatisticsContainer />,
        document.getElementById('userstatistics')
    );
}

if(document.getElementById('currentuserstatistics')) {
    ReactDOM.render(
        <CurrentUserStatisticsContainer />,
        document.getElementById('currentuserstatistics')
    );
}

if(document.getElementById('topscore')) {
    ReactDOM.render(
        <TopScoreContainer />,
        document.getElementById('topscore')
    );
}

DayActions.getDays();
