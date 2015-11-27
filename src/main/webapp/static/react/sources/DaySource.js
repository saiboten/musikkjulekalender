var request = require('superagent');

var DaySource = {

    fetchDays() {
        return new Promise(function (resolve, reject) {
            request.get('/days.json').end(function(err, res) {
                if(err) {
                    console.log("Nope, something is wrong: ", err);
                    reject(err);
                }
                else {
                    console.log("Got all songs :", res.body);
                    resolve(res.body);
                }
            });
        });
    }
};


module.exports = DaySource;
