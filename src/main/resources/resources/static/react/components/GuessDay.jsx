var React = require('react');
var debug = require('debug')('GuessDay');
var GuessAction = require('../actions/GuessAction');
var DayAction = require('../actions/DayAction');
var GuessStore = require('../stores/GuessStore');
var moment = require('moment');

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
        debug("Guess changed, refetching data just in case something has changed");
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
        else {
            formOrFeedback = (
                <form onSubmit={this.submit}>
                    <div className="form-group">
                        <label htmlFor="songInput">Sang</label>
                        <input type="text" ref="song" className="form-control" id="songInput" placeholder="Sang"
                               name="song" onChange={this.handleChange} value={this.state.guess.guess}/>
                    </div>
                    <p>{this.state.guessResponse.guess ? this.state.guessResponse.guess.feedback : ""}</p>
                    <button type="submit" className="btn btn-default">Lagre forslag</button>
                </form>
            );
        }

        return (
            <span><p>{this.props.day.description}</p>

                <audio src={this.props.day.link} preload="none" controls>
                    <a href={this.props.day.link}>Last ned låt</a>
                </audio>

                {this.props.user ? formOrFeedback : ""}</span>
        );
    }
});

module.exports = GuessDay;
