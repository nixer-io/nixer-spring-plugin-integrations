var newman = require('newman');

newman.run({
    collection: require('./test-regular-users.postman.json'), // can also provide a URL or path to a local JSON file.
    environment: require('./Local.postman_environment.json'),
    reporters: 'cli'
}, function (err) {
    if (err) { throw err; }
    console.log('collection run complete!');
});