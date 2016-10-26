var React = require('react');
var debug = require('debug')('UserStatistics');
var UserResultList = require('./UserResultList')

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
                        return <UserResultList key={day.id} day={day} userResult={this.props.userResult}  />;
                      }
                })}
            </div>
        );
    }
});

module.exports = UserStatistics;
