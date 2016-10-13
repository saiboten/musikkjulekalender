var gulp = require('gulp');

var source = require('vinyl-source-stream'); // Used to stream bundle for further handling
var browserify = require('browserify');
var watchify = require('watchify');
var reactify = require('reactify');
var concat = require('gulp-concat');
var babelify = require('babelify');
var notify = require("gulp-notify");

var watchLess = require('gulp-watch-less');
var less = require('gulp-less');

gulp.task('browserify', function() {
    var bundler = browserify({
        entries: ['./src/main/resources/resources/static/react/App.jsx'], // Only need initial file, browserify finds the deps
        transform: [reactify, babelify], // We want to convert JSX to normal javascript
        debug: false, // Gives us sourcemapping
        cache: {}, packageCache: {}, fullPaths: true // Requirement of watchify
    });
    var watcher  = watchify(bundler);

    return watcher
        .on('update', function () { // When any files update
            var updateStart = Date.now();
            console.log('Updating!');
            watcher.bundle() // Create new bundle that uses the cache for high performance
                .pipe(source('app.js'))
                // This is where you add uglifying etc.
                .pipe(gulp.dest('./target/classes/resources/static/react/dist'));
            console.log('Updated!', (Date.now() - updateStart) + 'ms');
        })

        .bundle().on('error', function(err) {
            return notify().write(err);
        }) // Create the initial bundle when starting the task
        .pipe(source('app.js'))
        .pipe(gulp.dest('./target/classes/resources/static/react/dist'));
});

// The default task (called when you run `gulp` from cli)
gulp.task('default', ['browserify'])
