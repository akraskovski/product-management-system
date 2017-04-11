exports.config = {
    framework: 'jasmine',
    capabilities: {
        browserName: 'chrome'
    },
    specs: ['./src/e2e/app-spec.js'],
    seleniumAddress: 'http://localhost:4444/wd/hub'
};