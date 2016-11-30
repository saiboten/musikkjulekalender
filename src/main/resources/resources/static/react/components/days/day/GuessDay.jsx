var React = require('react');
var debug = require('debug')('GuessDay');
var GuessAction = require('../../../actions/GuessAction');
var DayAction = require('../../../actions/DayAction');
var GuessStore = require('../../../stores/GuessStore');
var Inline = require('jsxstyle/Inline');
var Block = require('jsxstyle/Block');
var SongAudio = require('./SongAudio');

var GuessDay = React.createClass({

    componentDidMount() {
        GuessStore.listen(this.guessChanged);
    },

    componentWillUnmount() {
        GuessStore.unlisten(this.guessChanged);
    },

    getInitialState() {
        return {
            guess: "",
            guessResponse: GuessStore.getState()
        }
    },

    guessChanged() {
        this.setState({
            guessResponse: GuessStore.getState()
        });
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

        debug("Props: ", this.props);

        var answerThisDay = undefined;
        if(this.props.answers) {
            answerThisDay = this.props.answers.find(function(el) {
                return el.correctSongAnswer && el.day === this.props.today;
            },this);
        }

        debug("Answer this day: ", answerThisDay);

        var formOrFeedback = "";

        if(answerThisDay && answerThisDay.correctSongAnswer) {
            formOrFeedback = (<p>Du har allerede svart rett på denne oppgaven! Svaret
                var: {answerThisDay.guessedSong} </p>);
        }
        else if(this.state.guessResponse.guess && this.state.guessResponse.guess.correct) {
          formOrFeedback = (<p>Gratulerer, det var rett!</p>);
        }
        else {
            formOrFeedback = (
                <form onSubmit={this.submit}>
                    <div>
                        <input className="guess-form__input" placeholder="Sang" onChange={this.handleChange} value={this.state.guess.guess}/>
                        <Inline margin="10px" width="10%"><button className="guess-form__submitbutton" type="submit">Gjett!</button></Inline>
                    </div>
                    <p>{this.state.guessResponse.guess ? this.state.guessResponse.guess.feedback : ""}  </p>

                </form>
            );
        }

        return (
            <span>
                <p>{this.props.day.description}</p>
                <SongAudio link={this.props.day.link} />

                {this.props.user ? formOrFeedback : ""}</span>
        );
    }
});

module.exports = GuessDay;
