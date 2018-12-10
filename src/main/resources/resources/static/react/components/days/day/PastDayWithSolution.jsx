/**
 * Created by Tobias on 16.10.2016.
 */

var React = require("react");
var debug = require('debug')('ShowSolution');
var SongAudio = require('./SongAudio');

var PastDayWithSolution = React.createClass({

    getDescription(description) {
        return {
            __html: description
        }
    },

    createMarkup() {
        debug('Creating markup for the following solution video: ',this.props.day.optionalSolutionVideo);
        return {__html: this.props.day.optionalSolutionVideo};
    },

    render: function() {
        return (<div>
            <p><div dangerouslySetInnerHTML={this.getDescription(this.props.day.description)}></div></p>
            <p>
                <span>{this.props.day.solutionArtist} - {this.props.day.solutionSong} {this.props.day.optionalSolutionVideo ? <span className="youtube" dangerouslySetInnerHTML={this.createMarkup()}></span> : ""}</span>
            </p>
            <SongAudio link={this.props.day.link} />
        </div>);
    }
});

module.exports = PastDayWithSolution;
