var request = require('superagent');
var Promise = require('promise');

var AdminDaySource = {

    fetchDays() {
        return new Promise(function (resolve, reject) {
            request.get('/admin/alldata').end(function(err, res) {
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


module.exports = AdminDaySource;
