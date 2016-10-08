var React = require('react');
var debug = require('debug')('UserResultDay');
var UserResultDayUser = require('./UserResultDayUser.jsx');
var moment = require('moment');

var UserResultDay = React.createClass({

    render() {
        debug("props: ", this.props);

        var userList = this.props.userResult ? this.props.userResult[this.props.day.revealDateAsString] : undefined;

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
              <h3>{moment(this.props.day.revealDateAsString).format('DD. MMMM')}</h3>
          {maybeempty}
          </div>
          );
    }
});

module.exports = UserResultDay;
