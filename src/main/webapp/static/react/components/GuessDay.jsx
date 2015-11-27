var React = require('react');
var debug = require('debug')('guessday');
var GuessAction = require('../actions/GuessAction');

var GuessDay = React.createClass({

    componentDidMount() {
      debug("User!", this.props.user);
    },

    getInitialState() {
      return {
        guess: ""
      }
    },

    submit(e) {
      e.preventDefault();
      debug("Submit!", this.state.guess);
      GuessAction.guess(this.state.guess);
    },

    handleChange: function(event) {
     this.setState({guess: event.target.value});
   },

    render() {

      var correctAnswer = false;

      if(this.props.user && this.props.user.answers && this.props.user.answers[this.props.day.revealDate] && this.props.user.answers[this.props.day.revealDate].correctSong) {
        correctAnswer = true;
      }

      var formOrFeedback = "";
      if(this.props.guess.correct) {
           formOrFeedback = (<p>{this.props.guess.feedback}</p>);
      }
      else if(correctAnswer) {
        formOrFeedback = (<p>Du har allerede svart rett på denne oppgaven! Svaret var: {this.props.user.answers[this.props.day.revealDate].answerSong} </p>);
      }
      else {
        formOrFeedback = (
          <form onSubmit={this.submit}>
            <div className="form-group">
                <label for="songInput">Sang</label>
                <input type="text" ref="song" className="form-control" id="songInput" placeholder="Sang" name="song" onChange={this.handleChange} value={this.state.guess} />
            </div>
            <p>{this.props.guess.feedback}</p>

            <button type="submit" className="btn btn-default">Lagre forslag</button>
          </form>
      );
      }

        return (


          <div className="col-md-4 pane">
              <h1>{this.props.day.realDate}. desember</h1>

              <p>{this.props.day.description}</p>

              <audio src={this.props.day.link} controls>
                  <a href={this.props.day.link}>Last ned låt</a>
              </audio>

              {formOrFeedback}
            </div>
        );
    }
});

module.exports = GuessDay;
