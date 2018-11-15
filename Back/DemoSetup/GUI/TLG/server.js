var express = require('express');
var app = express();
app.use(express.static("TLG")); // myApp will be the same folder name.
app.get('/', function (req, res,next) {
 res.redirect('/'); 
});
app.listen(6060, '192.168.0.13');
console.log("MyProject Server is Listening on port 6060");
