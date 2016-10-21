var React = require('react');
var AltContainer = require('alt-container');
var DayStore = require('../stores/DayStore');
var DayActions = require('../actions/DayAction');
var Days = require('./Days.jsx');
var SingleGuessDay = require('./SingleGuessDay.jsx');
var Block = require('jsxstyle/Block');

var SingleGuessDayContainer = React.createClass({

    componentDidMount() {

    },

    render() {
        return (
            <Block width="700px" margin="20px auto" padding="10px"  borderRadius="5px" backgroundColor="white">
                <AltContainer store={DayStore}>
                    <SingleGuessDay />
                </AltContainer>
            </Block>
        );
    }
});

module.exports = SingleGuessDayContainer;
