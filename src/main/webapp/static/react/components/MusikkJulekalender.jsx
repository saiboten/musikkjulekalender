var React = require('react');
var AltContainer = require('alt/AltContainer');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');

var MusikkJulekalender = React.createClass({

    getInitialState() {
        return {
            error: ""
        }
    },

    componentDidMount() {
      DayActions.getDays();
      //Get things  SongActions.getSong();
    },

    render() {
        return (
          <AltContainer store={DayStore}>
                 <Days />
          </AltContainer>
        );
    }
});

module.exports = MusikkJulekalender;
