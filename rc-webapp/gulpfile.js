const gulp = require('gulp');
const typescript = require('gulp-typescript');
const tscConfig = require('./tsconfig.json');
const sourcemaps = require('gulp-sourcemaps');
const scss = require('gulp-scss');

var runSequence = require('run-sequence');

const tsProject = typescript.createProject('tsconfig.json');

const del = require('del');

const srcFolder = 'src/main/';

const CONFIG = {
    src: srcFolder,
    appSrc: srcFolder + 'app/',
    staticSrc: srcFolder + 'static/',
    dest: 'dist'
};

// clean the contents of the distribution directory
gulp.task('clean', function () {
  return del([CONFIG.dest + '/index.html', CONFIG.dest + '/app/**/*', CONFIG.dest + '/libs/**/*']);
});

gulp.task('DEV:scss', function () {
    gulp.src(
        CONFIG.appSrc + '**/*.scss'
    ).pipe(scss()).pipe(gulp.dest(CONFIG.dest + '/app'));
});

// TypeScript compile
gulp.task('DEV:compile', function () {
    return tsProject.src()
      .pipe(sourcemaps.init())
      .pipe(typescript(tscConfig.compilerOptions))
      .pipe(sourcemaps.write('.'))
      .pipe(gulp.dest(CONFIG.dest + '/app'));
});

// copy dependencies
gulp.task('DEV:libs', function() {
  return gulp.src([
      '@angular/**/*',
      'angular2-in-memory-web-api/**/*',
      'systemjs/**/*',
      'rxjs/**/*',
      'core-js/**/*',
      'zone.js/**/*',
      'reflect-metadata/**/*',
      'systemjs/**/*'
    ], {cwd : './node_modules', base: './node_modules'})
    .pipe(gulp.dest(CONFIG.dest + '/libs'))
});

// copy components html
gulp.task('DEV:component-html', function () {
    return gulp.src(['**/*.html'], { cwd: CONFIG.appSrc, base: CONFIG.src })
    .pipe(gulp.dest(CONFIG.dest))
});

// copy static assets - i.e. non TypeScript compiled source
gulp.task('DEV:static', function() {
    return gulp.src(['index.html', 'app/systemjs.config.js'], { cwd: CONFIG.staticSrc, base: CONFIG.staticSrc })
    .pipe(gulp.dest(CONFIG.dest))
});

gulp.task('DEV:build', function (callback) {
    runSequence('clean', ['DEV:compile', 'DEV:static', 'DEV:component-html', 'DEV:scss', 'DEV:libs'], callback);
});
gulp.task('default', ['build']);