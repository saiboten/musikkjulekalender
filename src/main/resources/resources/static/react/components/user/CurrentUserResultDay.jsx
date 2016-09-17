var React = require('react');
var debug = require('debug')('CurrentUserResultDay');

var CurrentUserResultDay = React.createClass({

    componentDidMount() {

    },

    render() {
      debug("this.props.user", this.props.user);
      debug("this.props.day", this.props.day);

      var answerComp = "";
      var answer = this.props.user.answers[this.props.day.revealDate];
      debug('answer',answer);

      if(answer && answer.correctSong) {
          answerComp = (<p>{this.props.day.realDate}. desember: {answer.answerSong} var riktig svar!</p>);
      }
      else {
          answerComp = (<p>{this.props.day.realDate}. desember: Du fant ikke riktig svar.</p>);
      }

      return (
        <div>
          <p>{answerComp}</p>
        </div>
        );
    }
});

module.exports = CurrentUserResultDay;
