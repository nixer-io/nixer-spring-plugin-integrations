var newman = require('newman');

var run = function (params) {

    var newman_args = {
        collection: require('./' + params.name + '.postman.json'),
        environment: require('./Local.postman_environment.json'),
        iterationData: params.dataFile,
        reporters: 'cli',
        cookiesWhitelistedDomains: [ "localhost" ]
    };
    if (params.reporter === 'html') {
        newman_args.reporters = 'htmlextra';
        newman_args.reporter = {
            htmlextra: {
                darkTheme: true,
                export: './test-results/' + params.name + '-report.html',
                logs: true
            }
        }
    }
    newman.run(newman_args, process.exit);
};


exports.run = run;