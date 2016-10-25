var React = require('react');
var debug = require('debug')('Days');
var Day = require('./Day.jsx')
var GuessDay = require('./GuessDay.jsx');
var GuessStore = require('../stores/GuessStore');
var Flex = require('jsxstyle/Flex');

var Days = React.createClass({

    render() {
        debug("Days props: ", this.props);

        return (
            <Flex flexDirection="row" flexWrap="wrap" padding="10px">
                {this.props.days.map((day, i) => {
                    if(day.realDate !== new Date(this.props.date).getDate().toString()) {
                      return <Day key={day.id} answers={this.props.answers} user={this.props.user} today={this.props.today} date={this.props.date} day={day} />;
                    }
                })}
            </Flex>
        );
    }
});

module.exports = Days;
