var React = require('react');
var debug = require('debug')('AdminDaySolution');
var AdminDayAction = require('../../actions/AdminDayAction');

var AdminDaySolution = React.createClass({

    componentDidMount() {
        debug("AdminDaySolution did mount", this.props.solution);
    },

    delete() {
      AdminDayAction.deleteSolution({
          id: this.props.dayId,
          solution: this.props.solution.solution
      });
    },

    render() {
        return (
            <li>Løsning: {this.props.solution.solution} <button onClick={this.delete}>Slett</button></li>
        );
    }
});

module.exports = AdminDaySolution;
