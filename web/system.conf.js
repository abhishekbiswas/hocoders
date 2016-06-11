System.config({
    paths: {
        'babel': 'node_modules/babel-core/browser.js',
        'systemjs': 'node_modules/systemjs/dist/system.js',
        'system-polyfills': 'node_modules/systemjs/dist/system-polyfills.js',
        'es6-module-loader': 'node_modules/es6-module-loader/dist/es6-module-loader.js',
        'traceur' : 'node_modules/traceur/bin/traceur-runtime.js',
        'angular2/*': 'node_modules/angular2/*.js',
        'rxjs/*': 'node_modules/angular2/node_modules/rxjs/*.js',
        '@angular': 'node_modules/@angular/*.js'
    },
    defaultJSExtensions: true
});