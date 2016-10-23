var React = require('react');
var debug = require('debug')('days');
var AdminDay = require('./AdminDay.jsx')
var GuessDay = require('./../GuessDay.jsx');
var GuessStore = require('../../stores/GuessStore');
var AdminAddDay = require('./AdminAddDay.jsx');
var Block = require('jsxstyle/Block');
var Flex = require('jsxstyle/Flex');

var AdminOverview = React.createClass({

    componentDidMount() {
        debug("Day! Woho");
    },

    render() {
        debug("this.props.days: ", this.props.days);
        return (
            <Flex backgroundColor="white" flexWrap="wrap" margin="5px auto" className="admin__container">
                {this.props.days.map((day, i) => {
                    if(day.realDate !== new Date(this.props.date).getDate().toString()) {
                        return <AdminDay key={day.revealDate} day={day} />;
                    }
                })}
                <AdminAddDay />
            </Flex>
        );
    }
});

module.exports = AdminOverview;
