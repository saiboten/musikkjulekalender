var React = require('react');
var debug = require('debug')('SingleGuessDay');
var moment = require('moment');
var Day = require('./Day.jsx');
var GuessDay = require('./GuessDay.jsx');
var AltContainer = require('alt-container');
var GuessStore = require('../stores/GuessStore');

var Days = React.createClass({

    componentDidMount() {
        debug("SingleGuessDay props");
    },

    render() {
        debug("Props: ", this.props);
        return (
            <span>
                {this.props.days.map((day, i) => {
                    debug("Reveal day as string + this.props.date: ", day.revealDateAsString, this.props.date);
                    if(day.revealDateAsString === this.props.date) {
                        return (<AltContainer store={GuessStore}>
                          <h1>Dagens oppgave</h1>
                            <div>
                                <h3>{moment(this.props.date).format("DD. MMMM")}</h3>
                                <GuessDay key={day.revealDateAsInt} today={this.props.today} date={this.props.date} day={day} answers={this.props.answers} user={this.props.user} />
                            </div>
                      </AltContainer>);
                    }
                })}
            </span>
        );
    }
});

module.exports = Days;
