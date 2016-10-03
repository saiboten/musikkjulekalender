var React = require('react');
var debug = require('debug')('days');
var AdminDay = require('./AdminDay.jsx')
var GuessDay = require('./../GuessDay.jsx');
var AltContainer = require('alt-container');
var GuessStore = require('../../stores/GuessStore');
var AdminAddDay = require('./AdminAddDay.jsx');

var AdminOverview = React.createClass({

    componentDidMount() {
        debug("Day! Woho");
    },

    render() {
        debug("this.props.days: ", this.props.days);
        return (
            <span>
                {this.props.days.map((day, i) => {
                    if(day.realDate !== new Date(this.props.date).getDate().toString()) {
                        return <AdminDay key={day.revealDate} day={day} />;
                    }
                })}
                <AdminAddDay />
            </span>
        );
    }
});

module.exports = AdminOverview;
