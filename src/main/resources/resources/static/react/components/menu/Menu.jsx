var React = require('react');

var Menu = React.createClass({

  getInitialState: function() {
    return {
      menuOpen: false
    }
  },

  toggleMenu: function() {
    this.setState( {
      menuOpen: !this.state.menuOpen
    })
  },

  frontPage: function() {
    window.location = "/";
  },

  about: function() {
    window.location = "/om";
  },

  logInOrOut: function()  {
    this.props.loggedIn === true ? window.location = "/logout" : window.location = "/secure";
  },

  render: function() {
    // Quick note: this.props.loggedIn is a string
    var loggedInLink = this.props.loggedIn==="true" ? (<a className="header__menu-item" href="/logout">Logg ut</a>) : (<a className="header__menu-item" href="/secure">Logg inn</a>);
    var openMenuContent = (
      <ul className="header__menu-dropdown-list">
       <li className="header__menu-dropdown-list-element" onClick={this.frontPage}>Forsiden</li>
       <li className="header__menu-dropdown-list-element" onClick={this.about}>Om</li>
       <li className="header__menu-dropdown-list-element" onClick={this.logInOrOut}>{this.props.loggedIn==="true" ? "Logg ut":"Logg inn"}</li>
      </ul>
    )

    return (
      <nav className="header">
        <div className="header__fullscreen">
          <a className="header__menu-item" href="/"> Forsiden </a>
          <a className="header__menu-item" href="/om"> Om </a>
          {loggedInLink}
        </div>

        <div className="fa fa-bars fa-3x header__menu-hamburger" onClick={this.toggleMenu}></div>
        {this.state.menuOpen ? openMenuContent: ""}

    </nav>
    );
  }
});

module.exports = Menu;
