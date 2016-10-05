var React = require('react');
var debug = require('debug')('UserStatistics');
var Day = require('./Day.jsx')
var GuessDay = require('./GuessDay.jsx');
var AltContainer = require('alt-container');
var GuessStore = require('../stores/GuessStore');
var UserResultDay = require('./UserResultDay.jsx')

var UserStatistics = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
    },

    render() {
        debug("User statistics props", this.props);

        return (
            <div>
                {this.props.days.map((day, i) => {
                      if(day.id === this.props.today) {
                        return <UserResultDay key={day.id} day={day} userResult={this.props.userResult}  />;
                      }

                })}
            </div>
        );
    }
});

module.exports = UserStatistics;
