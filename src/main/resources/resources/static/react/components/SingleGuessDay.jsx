var React = require('react');
var debug = require('debug')('SingleGuessDay');
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
        debug("Props: ", this.props);
        return (
            <span>
                {this.props.days.map((day, i) => {
                    debug("Reveal day as string + this.props.date: ", day.revealDateAsString, this.props.date);
                    if(day.revealDateAsString === this.props.date) {
                        debug("They are the same.");
                        return (<AltContainer store={GuessStore}>
                          <h1>Dagens oppgave</h1>
                          <GuessDay key={day.revealDateAsInt} date={this.props.date} day={day} answers={this.props.answers} user={this.props.user} />
                      </AltContainer>);
                    }
                })}
            </span>
        );
    }
});

module.exports = Days;
