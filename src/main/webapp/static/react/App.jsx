var React = require('react');
var MusikkJulekalender = require('./components/MusikkJulekalender.jsx');
var SingleGuessDayContainer = require('./components/SingleGuessDayContainer.jsx');
var UserStatisticsContainer = require('./components/UserStatisticsContainer.jsx');
var CurrentUserStatisticsContainer = require('./components/user/CurrentUserStatisticsContainer.jsx');
var TopScoreContainer = require('./components/topScore/TopScoreContainer.jsx');

if(document.getElementById('MusikkJulekalender')) {
    console.log("Musikkjulekalenderapp found, yeah!");
    React.render(
        <MusikkJulekalender />,
        document.getElementById('MusikkJulekalender')
    );
}

if(document.getElementById('frontpage')) {
    React.render(
        <SingleGuessDayContainer />,
        document.getElementById('frontpage')
    );
}

if(document.getElementById('userstatistics')) {
    console.log("Musikkjulekalenderapp found, yeah!");
    React.render(
        <UserStatisticsContainer />,
        document.getElementById('userstatistics')
    );
}

if(document.getElementById('currentuserstatistics')) {
    console.log("Current user statistics found, yeah!");
    React.render(
        <CurrentUserStatisticsContainer />,
        document.getElementById('currentuserstatistics')
    );
}

if(document.getElementById('topscore')) {
    console.log("Current topscore found, yeah!");
    React.render(
        <TopScoreContainer />,
        document.getElementById('topscore')
    );
}
