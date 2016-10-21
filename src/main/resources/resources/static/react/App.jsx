var React = require('react');
var ReactDOM = require('react-dom');
var debug = require('debug')('App');

var Main = require('./components/Main.jsx');
var AdminOverviewContainer = require('./components/admin/AdminOverviewContainer.jsx');
var DayActions = require('./actions/DayAction');

if(document.getElementById('main')) {
    ReactDOM.render(
        <Main />,
        document.getElementById('main')
    );
}

if(document.getElementById('admin')) {
    ReactDOM.render(
        <AdminOverviewContainer />,
        document.getElementById('admin')
    );
}

DayActions.getDays();
