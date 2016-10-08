var React = require('react');
var moment = require('moment');
var debug = require('debug')("DateHeader");

var DateHeader = React.createClass({
    render: function() {
        debug('this.props.unixDate', this.props.unixDate);
        return (<h3>{moment(this.props.unixDate).format('DD. MMMM')}</h3>);
    }
});

module.exports = DateHeader;