
var React = require('react');
var Block = require('jsxstyle/Block');

var SongAudio = React.createClass({

    render: function() {

        var audioProps = {
            src: this.props.link,
            preload: "none",
            controls: "yes"
        };

        return (
            <Block width="100%" component="audio" props={audioProps}>
                <a href={this.props.link}>Last ned låt</a>
            </Block>
        );
    }
});

module.exports = SongAudio;