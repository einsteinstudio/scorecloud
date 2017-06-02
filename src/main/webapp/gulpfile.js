var gulp = require('gulp');
var uglify = require('gulp-uglify');
var imagemin = require('gulp-imagemin');
var less = require('gulp-less');
var csso = require('gulp-csso');
var ejs = require("gulp-ejs");
var del = require('del');
var webpack = require('webpack');
var commonsPlugin = new webpack.optimize.CommonsChunkPlugin('common.js');

gulp.task('clean', function() {
    return del(['dist/*']);
});

gulp.task('js', function() {
    return gulp.src('./js/lib/zepto.js')
        .pipe(uglify())
        .pipe(gulp.dest('./dist/js/'));
});

gulp.task('img', function() {
    return gulp.src('./img/loading.gif')
        .pipe(imagemin({
            progressive: true,
            interlaced: true
        }))
        .pipe(gulp.dest('./dist/img/'));
});

gulp.task('less', function() {
    gulp.src('./src/less/*.less')
        .pipe(less())
        .pipe(csso())
        .on('error', function(e) {
            console.log(e)
        })
        .pipe(gulp.dest('./dist/css/'));
});

gulp.task('ejs', function() {
    gulp.src("./src/ejs/*.ejs")
        .pipe(ejs({
            msg: "Hello Gulp!"
        }, {
            ext: ".html"
        }))
        .pipe(gulp.dest("./dist/html/"));
});

gulp.task("webpack", function(callback) {
    // run webpack
    webpack({
        // configuration
    }, function(err, stats) {
        if (err) throw new gutil.PluginError("webpack", err);
        gutil.log("[webpack]", stats.toString({
            // output options
        }));
        callback();
    });
});

gulp.task('default', ['clean', 'img', 'less', 'js', 'ejs']);
