var React = require('react');
var AltContainer = require('alt/AltContainer');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');
var SingleGuessDay = require('./SingleGuessDay.jsx');

var SingleGuessDayContainer = React.createClass({

    componentDidMount() {

    },

    render() {
        return (
          <AltContainer store={DayStore}>
                 <SingleGuessDay />
          </AltContainer>
        );
    }
});

module.exports = SingleGuessDayContainer;
