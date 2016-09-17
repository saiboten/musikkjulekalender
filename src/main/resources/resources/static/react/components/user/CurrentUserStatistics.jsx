var React = require('react');
var debug = require('debug')('CurrentUserStatistics');
var CurrentUserResultDay = require('./CurrentUserResultDay.jsx')

var CurrentUserStatistics = React.createClass({

    componentDidMount() {
        debug("componentDidMount");
        debug("this.props.days",this.props.days);
    },

    render() {
        var userstat = this.props.days.map((day, i) => {
          if(day.solutionArtist || day.realDate === new Date(this.props.date).getDate().toString()) {
            return <CurrentUserResultDay day={day} user={this.props.user}  />;
          }
        });

        var userinfo = this.props.user ? (<span>Du er logget inn som <strong>{this.props.user.userName}</strong></span>) : "";
        var exist = this.props.user ? userstat : "Du må være logget inn for å få score";

        return (
          <div>
            <p>{userinfo}</p>
            {exist}
          </div>
        );
    }
});

module.exports = CurrentUserStatistics;
