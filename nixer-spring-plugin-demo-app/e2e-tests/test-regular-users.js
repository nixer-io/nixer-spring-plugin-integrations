var runner = require('./test-runner');
var argv = require('yargs').argv;

runner.run({
    name: 'test-regular-users',
    reporter: argv.reporter
});
