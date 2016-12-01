var React = require('react');
var debug = require('debug')('CurrentUserStatistics');
var CurrentUserResultDay = require('./CurrentUserResultDay.jsx')
var moment = require('moment');

var CurrentUserStatistics = React.createClass({

    componentDidMount() {
        debug("this.props.days",this.props.days);
    },

    render() {

        debug("this.props: ", this.props);


        var userstat = this.props.days.map((day, i) => {
          debug("Day: ", day);
          if(day.solutionArtist || day.revealDateAsString === this.props.today) {
            return <CurrentUserResultDay day={day} user={this.props.user} answers={this.props.answers}  />;
          }
        });

        var userinfo = this.props.user ? (<span>Du er logget inn som <strong>{this.props.user.userName}</strong></span>) : "";
        var exist = this.props.user ? userstat : "Du må være logget inn for å få score";

        return (
          <div>
            <p className="smallspace">{userinfo}</p>
            <p className="smallspace">{exist}</p>
          </div>
        );
    }
});

module.exports = CurrentUserStatistics;
