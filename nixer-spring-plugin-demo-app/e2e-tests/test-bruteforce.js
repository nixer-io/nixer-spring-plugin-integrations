var newman = require('newman');

newman.run({
    collection: require('./test-bruteforce.postman.json'), // can also provide a URL or path to a local JSON file.
    environment: require('./Local.postman_environment.json'),
    iterationData: './test-bruteforce.data.csv',
    reporters: 'htmlextra',
    reporter: {
        htmlextra: {
            darkTheme: true,
            export: './test-results/test-bruteforce-report.html', // If not specified, the file will be written to `newman/` in the current working directory.
            logs: true // optional, tells the reporter to display the console log statements in the report. This is False by default.
        }
    },
    cookiesWhitelistedDomains: [ "localhost" ]
}, function (err) {
    if (err) { throw err; }
    console.log('collection run complete!');
});