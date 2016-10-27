var request = require('superagent');
var Promise = require('promise');
var debug = require('debug')('AdminDaySource');

var AdminDaySource = {

    fetchDays() {
        return new Promise(function(resolve, reject) {
            request
                .get('/admin/alldata')
                .end(function(err, res) {
                if(err) {
                    debug("Nope, something is not right: ", err);
                    reject(err);
                }
                else {
                    debug("Got all songs :", res.body);
                    resolve(res.body);
                }
            });
        });
    },

    updateDay(day) {
        return new Promise(function(resolve, reject) {
            request.put('/admin/day')
                .send(day)
                .set('Accept', 'application/json')
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Update ok? ", res.body);
                        resolve(res.body);
                    }
                });
        });
    },

    deleteDay(id) {
        return new Promise(function(resolve, reject) {
            request.del('/admin/day/' + id)
                .set('Accept', 'application/json')
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Update ok? ", res.body);
                        resolve(res.body);
                    }
                });
        });
    },

    addDay(day) {
        return new Promise(function(resolve, reject) {
            request.post('/admin/day')
                .send(day)
                .set('Accept', 'application/json')
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Update ok? ", res.body);
                        resolve(res.body);
                    }
                });
        });
    },

    addSolution(solution) {
        return new Promise(function(resolve, reject) {
            request.post('/admin/addsolution/' + solution.id + '/' + solution.solution)
                .set('Accept', 'application/json')
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Update ok? ", res.body);
                        resolve(res.body);
                    }
                });
        });
    },

    deleteSolution(solution) {
        return new Promise(function(resolve, reject) {
            request.post('/admin/deletesolution/' + solution.id + '/' + solution.solution)
                .set('Accept', 'application/json')
                .end(function(err, res) {
                    if(err) {
                        debug("Nope, something is wrong: ", err);
                        reject(err);
                    }
                    else {
                        debug("Update ok? ", res.body);
                        resolve(res.body);
                    }
                });
        });
    }
};


module.exports = AdminDaySource;
