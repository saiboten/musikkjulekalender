var React = require('react');
var debug = require('debug')('Days');
var DaySelector = require('./day/DaySelector')
var GuessDay = require('./day/GuessDay');
var GuessStore = require('../../stores/GuessStore');
var Flex = require('jsxstyle/Flex');
var DayStore = require('../../stores/DayStore');
var Block = require('jsxstyle/Block');

var Days = React.createClass({

    getInitialState() {
        return DayStore.getState();
    },

    componentDidMount() {
        DayStore.listen(this.onChange);
    },

    componentWillUnmount() {
        DayStore.unlisten(this.onChange);
    },

    onChange(state) {
        this.setState(state);
    },

    render() {
        debug("Days props: ", this.props);

        return (
            <Block>
                <h1>Løsninger</h1>
                <Flex flexDirection="row" flexWrap="wrap" padding="10px">
                    {this.state.days.map((day, i) => {
                        if (day.realDate !== new Date(this.state.date).getDate().toString()) {
                            return <DaySelector key={day.id} answers={this.state.answers} user={this.state.user} today={this.state.today} date={this.state.date} day={day}/>;
                        }
                    })}
                </Flex>
            </Block>
        );
    }
});

module.exports = Days;
