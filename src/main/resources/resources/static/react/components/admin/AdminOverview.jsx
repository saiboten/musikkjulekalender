import { Block, Flex } from 'jsxstyle';

var React = require('react');
var debug = require('debug')('AdminOverview');
var AdminDay = require('./AdminDay')
var GuessDay = require('./../days/day/GuessDay');
var GuessStore = require('../../stores/GuessStore');
var AdminAddDay = require('./AdminAddDay');

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
