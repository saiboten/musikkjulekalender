import ReactAudioPlayer from 'react-audio-player';
import { Block } from 'jsxstyle';

var React = require('react');

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