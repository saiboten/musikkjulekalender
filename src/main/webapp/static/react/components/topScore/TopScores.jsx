var React = require('react');
var debug = require('debug')('TopScores');
var TopScore = require('./TopScore.jsx')

var TopScores = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
    },

    /*render() {

        debug("toplist", this.props.topList);
        return (
            <ol>
              {this.props.topList.map((topListUser, i) => {
                      return (<TopScore topListUser={topListUser}  />);
              })}
            <ol>)
          }*/

    render() {

      debug("toplist", this.props.topList);

        return (
        <ol>
          {this.props.topList.map((topListUser, i) => {
                  return (<TopScore user={this.props.user} topListUser={topListUser}  />);
          })}
        </ol>
        );
    }
});

module.exports = TopScores;
