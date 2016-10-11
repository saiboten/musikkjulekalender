var React = require('react');
var AltContainer = require('alt-container');
var AdminDayStore = require('../../stores/AdminDayStore');
var AdminDayActions = require('../../actions/AdminDayAction');
var AdminOverview = require('./AdminOverview.jsx');
var keycloak = require('./../../sources/KeycloakSource');
var debug = require('debug')('AdminOverviewContainer');

var AdminOverviewContainer = React.createClass({

    componentDidMount() {
        keycloak.init().success((authenticated) => {
            debug("Init OK, lets fetch some days!");
            if(authenticated) {
                AdminDayActions.getDays();
            }
        }).error((e) => {
            debug("Something went terribly wrong",e);
        });
    },

    render() {
        return (
          <AltContainer store={AdminDayStore}>
                 <AdminOverview />
          </AltContainer>
        );
    }
});

module.exports = AdminOverviewContainer;
