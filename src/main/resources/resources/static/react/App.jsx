var React = require('react');
var ReactDOM = require('react-dom');

var MusikkJulekalender = require('./components/MusikkJulekalender.jsx');
var SingleGuessDayContainer = require('./components/SingleGuessDayContainer.jsx');
var UserStatisticsContainer = require('./components/UserStatisticsContainer.jsx');
var CurrentUserStatisticsContainer = require('./components/user/CurrentUserStatisticsContainer.jsx');
var TopScoreContainer = require('./components/topScore/TopScoreContainer.jsx');
var AdminOverviewContainer = require('./components/admin/AdminOverviewContainer.jsx');

if(document.getElementById('MusikkJulekalender')) {
    console.log("Musikkjulekalenderapp found, yeah!");
    ReactDOM.render(
        <MusikkJulekalender />,
        document.getElementById('MusikkJulekalender')
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
    console.log("Musikkjulekalenderapp found, yeah!");
    ReactDOM.render(
        <UserStatisticsContainer />,
        document.getElementById('userstatistics')
    );
}

if(document.getElementById('currentuserstatistics')) {
    console.log("Current user statistics found, yeah!");
    ReactDOM.render(
        <CurrentUserStatisticsContainer />,
        document.getElementById('currentuserstatistics')
    );
}

if(document.getElementById('topscore')) {
    console.log("Current topscore found, yeah!");
    ReactDOM.render(
        <TopScoreContainer />,
        document.getElementById('topscore')
    );
}
