var React = require('react');
var AltContainer = require('alt/AltContainer');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');
var SingleGuessDay = require('./SingleGuessDay.jsx');

var MusikkJulekalenderFrontPage = React.createClass({

    componentDidMount() {
        DayActions.getDays();
    //Get things  SongActions.getSong();
    },

    render() {
        return (
          <AltContainer store={DayStore}>
                 <SingleGuessDay />
          </AltContainer>
        );
    }
});

module.exports = MusikkJulekalenderFrontPage;
