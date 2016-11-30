var React = require('react');
var debug = require('debug')('Day');
var GuessDay = require('./GuessDay');
var DateHeader = require('./../../DateHeader');
var PastDayWithSolution = require('./PastDayWithSolution');
var PastDayWithoutSolution = require('./PastDayWithoutSolution');
var Block = require('jsxstyle/Block');
var Flex = require('jsxstyle/Flex');

var DaySelector = React.createClass({

    getInitialState() {
        return {
            showSolution: false
        }
    },

    showSolution() {
      debug("Showing solution");
        this.setState({
            showSolution: true
        });
    },

    createMarkup() {
        debug('Creating markup', this.props.day.optionalSolutionVideo);
        return {__html: this.props.day.optionalSolutionVideo};
    },

    render() {
        var day = "";

        debug("Day.props", this.props);

        if(this.state.showSolution && this.props.day.solutionArtist) {
            day = (
                <PastDayWithSolution day={this.props.day}/>
            );
        }
        else if(this.props.day.revealDateAsString === this.props.date) {
            day = (<GuessDay date={this.props.day.revealDateAsString} today={this.props.today} day={this.props.day}
                             answers={this.props.answers} user={this.props.user}/>);
        }
        else if(this.props.day.description) {
            day = (
                <PastDayWithoutSolution day={this.props.day} showSolutionCallback={this.showSolution}/>
            );
        }
        else {
            day = (<p>Luke ikke åpnet</p>);
        }

        return (
            <Block className="day__container">
                <DateHeader unixDate={this.props.day.revealDateAsString}></DateHeader>
                {day}
            </Block>
        );
    }
});

module.exports = DaySelector;
