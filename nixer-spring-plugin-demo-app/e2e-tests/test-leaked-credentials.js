var runner = require('./test-runner');
var argv = require('yargs').argv;

runner.run({
    name: 'test-leaked-credentials',
    reporter: argv.reporter
});
