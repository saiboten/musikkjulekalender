var React = require('react');
var debug = require('debug')('HighScoreElement');

var HighScoreElement = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
        debug(this.props.user);
    },

    render() {
        return (<strong>{this.props.topListUser.user}: {this.props.topListUser.score}</strong>);
    }
});

module.exports = HighScoreElement;
