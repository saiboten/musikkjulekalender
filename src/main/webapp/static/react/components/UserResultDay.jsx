var React = require('react');
var debug = require('debug')('UserResultDay');
var UserResultDayUser = require('./UserResultDayUser.jsx');

var UserResultDay = React.createClass({

    componentDidMount() {
        debug("this.props.userResult", this.props.userResult);
        debug("this.props.day", this.props.day);

    },

    render() {
        var userList = this.props.userResult[this.props.day.revealDate];
        debug('userList.users', userList.users);
        var maybeempty = userList.users.length === 0 ? <p>Ingen riktige svar</p> : ""

        return (
          <div>
          <p>{this.props.day.realDate}. desember</p>
          {maybeempty}
          <ul>
          { userList.users.map((user, i) => {
            debug('User: ', user);
            return <UserResultDayUser user={user}  />;
          })}
          </ul>
          </div>
          );
    }
});

module.exports = UserResultDay;
