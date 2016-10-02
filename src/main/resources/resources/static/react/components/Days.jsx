var React = require('react');
var debug = require('debug')('days');
var Day = require('./Day.jsx')
var GuessDay = require('./GuessDay.jsx');
var GuessStore = require('../stores/GuessStore');

var Days = React.createClass({

    render() {
        debug("Days props: ", this.props);

        return (
            <div className="col-md-12">
                {this.props.days.map((day, i) => {
                    if(day.realDate !== new Date(this.props.date).getDate().toString()) {
                      return <Day key={day.id} answers={this.props.answers} user={this.props.user} date={this.props.date} day={day} />;
                    }
                })}
            </div>
        );
    }
});

module.exports = Days;
