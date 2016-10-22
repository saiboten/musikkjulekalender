var React = require('react');
var debug = require('debug')('Day');
var moment = require('moment');
var GuessDay = require('./GuessDay.jsx');
var DateHeader = require('./DateHeader.jsx');
var ShowSolution = require('./ShowSolution.jsx');
var Day4Realz = require('./Day4Realz.jsx');
var Block = require('jsxstyle/Block');
var Flex = require('jsxstyle/Flex');

var Day = React.createClass({

    getInitialState() {
        return {
            showSolution: false
        }
    },

    showSolution() {
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
                <ShowSolution day={this.props.day}/>
            );
        }
        else if(this.props.day.revealDateAsString === this.props.date) {
            day = (<GuessDay date={this.props.day.revealDateAsString} today={this.props.today} day={this.props.day}
                             answers={this.props.answers} user={this.props.user}/>);
        }
        else if(this.props.day.description) {
            day = (
                <Day4Realz day={this.props.day} showSolutionCallback={this.showSolution}/>
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

module.exports = Day;
