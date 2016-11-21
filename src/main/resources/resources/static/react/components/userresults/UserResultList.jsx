var React = require('react');
var debug = require('debug')('UserResultList');
var UserResultElement = require('./UserResultElement');
var moment = require('moment');
var Block = require('jsxstyle/Block');

var UserResultList = React.createClass({

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
             return <UserResultElement user={user}  />;
           })}
         </ul>);
        }
        else {
            maybeempty = (<Block padding="10px">Ingen riktige svar</Block>);
       }
        return (
          <div>
              <h3>{moment(this.props.day.revealDateAsString).format('DD. MMMM')}</h3>
          {maybeempty}
          </div>
          );
    }
});

module.exports = UserResultList;
