/**
 * Created by Tobias on 16.10.2016.
 */

var React = require("react");
var debug = require('debug')('ShowSolution');

var ShowSolution = React.createClass({

    createMarkup() {
        debug('Creating markup for the following solution video: ',this.props.day.optionalSolutionVideo);
        return {__html: this.props.day.optionalSolutionVideo};
    },

    render: function() {
        return (<div>
            <p>{this.props.day.description}</p>
            <p>
                <span>{this.props.day.solutionArtist} - {this.props.day.solutionsSong} {this.props.day.optionalSolutionVideo ? <span className="youtube" dangerouslySetInnerHTML={this.createMarkup()}></span> : ""}</span>
            </p>
            <audio src={this.props.day.link} controls>
                <a href={this.props.day.link}>Last ned låt</a>
            </audio>
        </div>);
    }
});

module.exports = ShowSolution;

