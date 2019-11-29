var runner = require('./test-runner');
var argv = require('yargs').argv;

runner.run({
    name: 'test-ip-filter',
    dataFile: './test-ip-filter.data.csv',
    reporter: argv.reporter
});
