var React = require('react');
var debug = require('debug')('UserResultDay');
var UserResultDayUser = require('./UserResultDayUser.jsx');

var UserResultDay = React.createClass({

    componentDidMount() {
      //  debug("this.props.userResult", this.props.userResult);
      //  debug("this.props.day", this.props.day);
    },

    render() {
        debug("props: ", this.props);

        var userList = this.props.userResult ? this.props.userResult[this.props.day.revealDate] : undefined;

        debug("userList", userList);
        var maybeempty = "";
        if(userList && userList.users && userList.users.length > 0) {

          var copy = userList.users;
          copy.sort(function(a,b) {
              debug("Sorting :", a,b);
              if(a.time > b.time) {
                return 1;
              }
              else {
                return -1;
              }
          });

          maybeempty = (<ul>
           {copy.map((user, i) => {
             return <UserResultDayUser user={user}  />;
           })}
         </ul>);
        }
        else {
            maybeempty = (<p>Ingen riktige svar</p>);
       }
        return (
          <div>
          <p>{this.props.day.revealDate}</p>
          {maybeempty}
          </div>
          );
    }
});

module.exports = UserResultDay;
