/**
 * Created by Tobias on 16.10.2016.
 */
var React = require('react');
var Block = require('jsxstyle/Block');
var Flex = require('jsxstyle/Flex');
var Inline = require('jsxstyle/Inline');
var SongAudio = require('./SongAudio');



var PastDayWithoutSolution = React.createClass({
    render() {
        return (<Block backgroundColor="white" padding="10px">
            <Block >{this.props.day.description}</Block>
            <p>
                <button onClick={this.props.showSolutionCallback}>Vis fasit</button>
            </p>
            <SongAudio link={this.props.day.link}/>

        </Block>);
    }
});

module.exports = PastDayWithoutSolution;
