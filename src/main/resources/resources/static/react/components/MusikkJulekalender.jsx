var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');
var keycloak = require('./../sources/KeycloakSource');
var debug = require('debug')('MusikkJulekalender');

var MusikkJulekalender = React.createClass({

    getInitialState() {
        return {
            error: ""
        }
    },

    componentDidMount() {
      //Get things  SongActions.getSong();

        keycloak.init().success(() => {
            debug("Init OK, lets fetch some days!");
            DayActions.getDays();
        }).error((e) => {
            debug("Something went terribly wrong",e);
        });
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
