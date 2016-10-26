var React = require('react');
var AltContainer = require('alt-container');
var AdminDayStore = require('../../stores/AdminDayStore');
var AdminDayActions = require('../../actions/AdminDayAction');
var AdminOverview = require('./AdminOverview');
var debug = require('debug')('AdminOverviewContainer');

var AdminOverviewContainer = React.createClass({

    componentDidMount() {

       AdminDayActions.getDays();
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
