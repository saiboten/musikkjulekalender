var React = require('react');
var AltContainer = require('alt/AltContainer');
var AdminDayStore = require('../../stores/AdminDayStore');
var AdminDayActions = require('../../actions/AdminDayAction');
var AdminOverview = require('./AdminOverview.jsx');

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
