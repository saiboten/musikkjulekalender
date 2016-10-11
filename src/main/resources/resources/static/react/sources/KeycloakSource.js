var debug = require('debug')('KeycloakSource');
var keycloak = Keycloak('http://localhost:8080/static/js/keycloak.json');

var ret = {
    init: function() {
        return keycloak.init({ onLoad: 'check-sso' });
    },

    getToken: function() {
        return keycloak.token;
    }
};

module.exports = ret;