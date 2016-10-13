var React = require('react');
var debug = require('debug')('UserResultDayUser');
var moment = require('moment');

var UserResultDayUser = React.createClass({
    render() {

        var momentTime = moment(this.props.user.time).format("HH:mm");

        return (
            <li>
              <p>{this.props.user.name}: {momentTime} </p>
            </li>
        );
    }
});

module.exports = UserResultDayUser;
