var React = require('react');
var debug = require('debug')('HighScoreList');
var HighScoreElement = require('./HighScoreElement')

var HighScoreList = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
    },

    render() {

      debug("toplist", this.props.topList);

        return (
        <ol>
          {this.props.topList.filter(function(user) {
            return user.score > 0;
          }).map((topListUser, index) => {
                  return (<HighScoreElement key={index} user={this.props.user} topListUser={topListUser}  />);
          })}
        </ol>
        );
    }
});

module.exports = HighScoreList;
