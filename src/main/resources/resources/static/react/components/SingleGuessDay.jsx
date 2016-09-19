var React = require('react');
var debug = require('debug')('days');
var moment = require('moment');
var Day = require('./Day.jsx');
var GuessDay = require('./GuessDay.jsx');
var AltContainer = require('alt/AltContainer');
var GuessStore = require('../stores/GuessStore');

var Days = React.createClass({

    componentDidMount() {
        debug("Day! Woho");
    },

    render() {
        return (
            <span>
                {this.props.days.map((day, i) => {
                    if(moment(day.revealDateAsInt).format('YYYY MM DD') === moment(this.props.date).format('YYYY MM DD') ) {
                      return (<AltContainer store={GuessStore}>
                          <h1>Dagens oppgave</h1>
                          <GuessDay key={day.revealDateAsInt} day={day} user={this.props.user} />
                      </AltContainer>);
                    }
                })}
            </span>
        );
    }
});

module.exports = Days;
