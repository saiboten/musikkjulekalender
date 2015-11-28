var React = require('react');
var debug = require('debug')('UserResultDayUser');

var UserResultDayUser = React.createClass({

    componentDidMount() {

    },

    render() {
        debug("this.props.user", this.props.user);

        var day = "";
        var date = new Date(this.props.user.time);
        var timeOfResult = date.getHours() + ":" + date.getMinutes();

        debug(timeOfResult);

        return (
            <li>
              <p>{this.props.user.name}: {timeOfResult}</p>
            </li>
        );
    }
});

module.exports = UserResultDayUser;
