var React = require('react');
var debug = require('debug')('day');
var moment = require('moment');

var AdminDay = React.createClass({

    componentDidMount() {
        debug("this.day", this.props.day);
    },

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
      debug('Creating markup',this.props.day.optionalSolutionVideo);
      return {__html: this.props.day.optionalSolutionVideo};
    },

    render() {
        var day = "";

        return (
            <div className="col-md-6 pane">
              <h3>{moment(this.props.day.revealDateAsString).format('DD. MMMM')}</h3>
                <div>
                    <p>{this.props.day.description}</p>
                    <p>
                        <span>{this.props.day.solutionArtist} - {this.props.day.solutionsSong} {this.props.day.optionalSolutionVideo ? <span className="youtube" dangerouslySetInnerHTML={this.createMarkup()}></span> : ""}</span>
                    </p>
                    <audio src={this.props.day.link} controls>
                        <a href={this.props.day.link}>Last ned låt</a>
                    </audio>
                </div>
            </div>
        );
    }
});

module.exports = AdminDay;
