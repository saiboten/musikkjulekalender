var React = require('react');
var ReactDOM = require('react-dom');
var debug = require('debug')('App');

var Main = require('./components/Main');
var AdminOverviewContainer = require('./components/admin/AdminOverviewContainer');
var DayActions = require('./actions/DayAction');
var Menu = require('./components/menu/Menu');

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

if(document.getElementById('menuLoggedIn')) {
    ReactDOM.render(
        <Menu loggedIn="true" />,
        document.getElementById('menuLoggedIn')
    );
}

if(document.getElementById('menuLoggedOut')) {
    ReactDOM.render(
        <Menu loggedIn="false" />,
        document.getElementById('menuLoggedOut')
    );
}



DayActions.getDays();
