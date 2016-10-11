var request = require('superagent');
var Promise = require('promise');
var debug = require("debug")("DaySource");
var keycloak = require('./KeycloakSource');


var DaySource = {

    fetchDays() {
        debug("Keycloak token ", keycloak.getToken());
        debug("Keycloak ", keycloak);


        return new Promise(function (resolve, reject) {
            request
                .get('/alldata')
                .set('Authorization', 'Bearer ' +keycloak.getToken())
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Got all songs :", res.body);
                        resolve(res.body);
                    }
                });
        });
    }
};


module.exports = DaySource;
