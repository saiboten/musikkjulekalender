/**
 * Created by Tobias on 16.10.2016.
 */
var React = require('react');

var Day4Realz = React.createClass({
    render() {
       return (<span>
              <p>{this.props.day.description}</p>
              <p><button onClick={this.props.showSolutionCallback}>Vis fasit</button></p>
              <audio className="audio" src={this.props.day.link} preload="none" controls>
                <a href={this.props.day.link}>Last ned låt</a>
              </audio>
            </span>);
    }
});

module.exports = Day4Realz;
