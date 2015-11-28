var React = require('react');
var MusikkJulekalender = require('./components/MusikkJulekalender.jsx');
var MusikkJulekalenderFrontPage = require('./components/MusikkJulekalenderFrontPage.jsx');
var UserStatisticsContainer = require('./components/UserStatisticsContainer.jsx');
var CurrentUserStatisticsContainer = require('./components/user/CurrentUserStatisticsContainer.jsx');
var TopScoreContainer = require('./components/topScore/TopScoreContainer.jsx');

console.log("Yeah?");
console.log(document.getElementById('frontpage'));

if(document.getElementById('MusikkJulekalender')) {
    console.log("Musikkjulekalenderapp found, yeah!");
    React.render(
        <MusikkJulekalender />,
        document.getElementById('MusikkJulekalender')
    );
}

if(document.getElementById('frontpage')) {
    React.render(
        <MusikkJulekalenderFrontPage />,
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
