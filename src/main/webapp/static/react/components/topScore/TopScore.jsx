var React = require('react');
var debug = require('debug')('TopScore');

var TopScore = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
    },

    render() {
        return (
          <li>{this.props.topListUser.user}: {this.props.topListUser.score}</li>
        );
    }
});

module.exports = TopScore;
