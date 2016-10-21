/**
 * Created by Tobias on 16.10.2016.
 */
var React = require('react');
var Block = require('jsxstyle/Block');
var Flex = require('jsxstyle/Flex');
var Inline = require('jsxstyle/Inline');


var Day4Realz = React.createClass({
    render() {
       return (<Block backgroundColor="white" padding="10px">
              <Block >{this.props.day.description}</Block>
              <p><button onClick={this.props.showSolutionCallback}>Vis fasit</button></p>
              <audio className="audio" src={this.props.day.link} preload="none" controls>
                <a href={this.props.day.link}>Last ned låt</a>
              </audio>
            </Block>);
    }
});

module.exports = Day4Realz;
