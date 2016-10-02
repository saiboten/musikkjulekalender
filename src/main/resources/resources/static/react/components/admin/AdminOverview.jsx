var React = require('react');
var debug = require('debug')('days');
var AdminDay = require('./AdminDay.jsx')
var GuessDay = require('./../GuessDay.jsx');
var AltContainer = require('alt-container');
var GuessStore = require('../../stores/GuessStore');

var AdminOverview = React.createClass({

    componentDidMount() {
        debug("Day! Woho");
    },

    render() {
        debug("this.props.days: ", this.props.days);
        return (
            <div className="col-md-12">

                {this.props.days.map((day, i) => {
                    if(day.realDate !== new Date(this.props.date).getDate().toString()) {
                        return <AdminDay key={day.revealDate} day={day} />;
                    }
                })}
            </div>
        );
    }
});

module.exports = AdminOverview;
