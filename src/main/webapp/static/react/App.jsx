var React = require('react');
var MusikkJulekalender = require('./components/MusikkJulekalender.jsx');
var MusikkJulekalenderFrontPage = require('./components/MusikkJulekalenderFrontPage.jsx');
var UserStatisticsContainer = require('./components/UserStatisticsContainer.jsx');

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
