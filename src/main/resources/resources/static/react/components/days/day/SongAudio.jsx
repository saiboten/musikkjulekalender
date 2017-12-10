import ReactAudioPlayer from 'react-audio-player';

var React = require('react');
var Block = require('jsxstyle/Block');


var SongAudio = React.createClass({

    render: function() {
        /* var audioProps = {
            src: this.props.link,
            preload: "none",
            controls: "yes"
        };

        <Block width="100%" component="audio" props={audioProps}>
        <a href={this.props.link}>Last ned låt</a>
        </Block> */

        return (
            <ReactAudioPlayer
            src={this.props.link}
            controls
            className="audio-element"
          />
        );
    }
});

module.exports = SongAudio;