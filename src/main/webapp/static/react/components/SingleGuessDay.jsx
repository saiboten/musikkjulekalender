var React = require('react');
var debug = require('debug')('days');
var Day = require('./Day.jsx')
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
                    if(day.realDate === new Date(this.props.date).getDate().toString()) {
                      return (<AltContainer store={GuessStore}>
                          <GuessDay class="col-md-12 pane" key={day.revealDate} day={day} user={this.props.user} />
                      </AltContainer>);
                    }
                })}
            </span>
        );
    }
});

module.exports = Days;
