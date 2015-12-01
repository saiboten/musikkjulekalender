var React = require('react');
var debug = require('debug')('TopScore');

var TopScore = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
        debug(this.props.user);
    },

    render() {
        var user = this.props.topListUser.user === this.props.user.userNameNotMail ? (<strong>{this.props.topListUser.user}: {this.props.topListUser.score}</strong>) : this.props.topListUser.user + " " + this.props.topListUser.score;
        return (
          <li>{user}</li>
        );
    }
});

module.exports = TopScore;
