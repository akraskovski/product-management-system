var SpecReporter = require('jasmine-spec-reporter');

exports.config = {
    baseUrl: 'http://localhost:3000/',
    specs: [],
    framework: 'jasmine',
    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000
    },
    useAllAngular2AppRoots: true,
    allScriptsTimeout: 110000,
    directConnect: true,
    capabilities: {
        'browserName': 'chrome',
        'chromeOptions': {
            'args': ['show-fps-counter=true']
        }
    },
    onPrepare: function() {
        jasmine.getEnv().addReporter(new SpecReporter());
    }
};