var request = require('superagent');
var Promise = require('promise');
var debug = require("debug")("DaySource");


var DaySource = {

    fetchDays() {
        return new Promise(function (resolve, reject) {
            request
                .get('/alldata')
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
