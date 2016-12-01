var React = require('react');
var debug = require('debug')('HighScoreElement');

var HighScoreElement = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
        debug(this.props.user);
    },

    render() {
        return (<li>{this.props.topListUser.user}: <strong>{this.props.topListUser.score}</strong></li>);
    }
});

module.exports = HighScoreElement;
