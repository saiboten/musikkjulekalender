var request = require('superagent');
var debug = require('debug')('GuessSource');
var Promise = require('promise');

var GuessSource = {

    guess(song) {
        return new Promise(function (resolve, reject) {
            request.get('/answer.json?song=' + song).end(function(err, res) {
                if(err) {
                    debug("Nope, something is wrong: ", err);
                    reject(err);
                }
                else {
                    debug("Got the result :", res.body);
                    resolve(res.body);
                }
            });
        });
    }
};


module.exports = GuessSource;
