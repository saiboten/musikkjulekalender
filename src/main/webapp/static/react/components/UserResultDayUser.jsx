var React = require('react');
var debug = require('debug')('UserResultDayUser');

var UserResultDayUser = React.createClass({
    render() {
        var padZeroes = function(input) {
          if(/^\d{1}$/.test(input)) {
            return "0"+input.toString();
          }
          return input;
        }

        var day = "";
        var date = new Date(this.props.user.time);
        var timeOfResult = padZeroes(date.getHours()) + ":" + padZeroes(date.getMinutes());

        return (
            <li>
              <p>{this.props.user.name}: {timeOfResult}</p>
            </li>
        );
    }
});

module.exports = UserResultDayUser;
