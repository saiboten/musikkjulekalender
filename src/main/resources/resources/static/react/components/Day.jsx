var React = require('react');
var debug = require('debug')('day');
var moment = require('moment');

var Day = React.createClass({

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

        if(this.state.showSolution && this.props.day.solutionArtist) {
          day = (
            <div>
              <p>{this.props.day.description}</p>
              <p>
                <span>{this.props.day.solutionArtist} - {this.props.day.solutionsSong} {this.props.day.optionalSolutionVideo ? <span className="youtube" dangerouslySetInnerHTML={this.createMarkup()}></span> : ""}</span>
              </p>
              <audio src={this.props.day.link} controls>
                <a href={this.props.day.link}>Last ned låt</a>
              </audio>
            </div>);
        }
        else if (this.props.day.solutionArtist) {
           day = (
             <span>
              <p>{this.props.day.description}</p>
              <p><button onClick={this.showSolution}>Vis fasit</button></p>
              <audio class="audio" src={this.props.day.link} preload="none" controls>
                <a href={this.props.day.link}>Last ned låt</a>
              </audio>
            </span>);
        }
        else {
          day =   <p>Luke ikke åpnet</p>;
        }

        return (
            <div className="col-md-6 pane">
              <h3>{moment(this.props.day.revealDateAsInt).format('DD. MMMM')}</h3>
              {day}
            </div>
        );
    }
});

module.exports = Day;
