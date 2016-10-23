var React = require('react');
var debug = require('debug')('AdminDaySolution');

var AdminDaySolution = React.createClass({

    componentDidMount() {
        debug("AdminDaySolution did mount", this.props.solution);
    },

    render() {
        return (
            <li>Løsning: {this.props.solution.solution}</li>
        );
    }
});

module.exports = AdminDaySolution;
