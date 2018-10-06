import { Block } from 'jsxstyle';

var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../../../stores/DayStore');
var DayActions = require('../../../actions/DayAction');
var Days = require('./../Days');
var SingleGuessDay = require('./SingleGuessDay');

var SingleGuessDayContainer = React.createClass({

    componentDidMount() {

    },

    render() {
        return (
            <Block className="guess-day__container" margin="0 auto" padding="5px"  borderRadius="5px" backgroundColor="white">
                <AltContainer store={DayStore}>
                    <SingleGuessDay />
                </AltContainer>
            </Block>
        );
    }
});

module.exports = SingleGuessDayContainer;
