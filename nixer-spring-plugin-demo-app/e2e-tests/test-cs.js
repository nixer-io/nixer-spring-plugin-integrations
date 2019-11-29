var runner = require('./test-runner');
var argv = require('yargs').argv;

runner.run({
    name: 'test-cs',
    dataFile: './test-cs.data.csv',
    reporter: argv.reporter
});
