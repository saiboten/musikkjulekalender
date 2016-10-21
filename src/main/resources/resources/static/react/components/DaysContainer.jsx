var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');
var debug = require('debug')('DaysContainer');
var Block = require('jsxstyle/Block');

var DaysContainer = React.createClass({

    getInitialState() {
        return {
            error: ""
        }
    },

    componentDidMount() {
        //Get things  SongActions.getSong();
    },

    render() {
        return (
            <Block>
                <h1>Løsninger</h1>
                <AltContainer store={DayStore}>
                    <Days />
                </AltContainer>
            </Block>
        );
    }
});

module.exports = DaysContainer;
