var React = require('react');
var debug = require('debug')('CurrentUserResultDay');
var moment = require('moment');
var DateHeader = require('../DateHeader');

var CurrentUserResultDay = React.createClass({

    componentDidMount() {

    },

    render() {
      debug("this.props.user", this.props.user);
      debug("this.props.day", this.props.day);
      debug("this.props.answers", this.props.answers);

      var answerComp = "";
      var answer = this.props.answers.filter(answer => {
        return answer.day === this.props.day.id && answer.correctSongAnswer;
      })[0];
      debug('answer',answer);

      if(answer) {
          answerComp = (<p>{moment(this.props.day.revealDate).format('DD. MMMM')}: {answer.guessedSong} var riktig svar!</p>);
      }
      else {
          answerComp = (<p>{moment(this.props.day.revealDate).format('DD. MMMM')}: Du fant ikke riktig svar.</p>);
      }

      return (
        <div>
          <p>{answerComp}</p>
        </div>
        );
    }
});

module.exports = CurrentUserResultDay;
