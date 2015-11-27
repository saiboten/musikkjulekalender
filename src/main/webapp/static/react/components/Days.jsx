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
            <div className="col-md-12">
                {this.props.days.map((day, i) => {
                    if(day.realDate === new Date(this.props.date).getDate().toString()) {
                      return (<AltContainer store={GuessStore}>
                          <GuessDay key={day.revealDate} day={day} user={this.props.user} />
                      </AltContainer>);
                    }
                    else {
                        return <Day key={day.revealDate} day={day} />;
                    }
                })}
            </div>
        );
    }
});

module.exports = Days;
