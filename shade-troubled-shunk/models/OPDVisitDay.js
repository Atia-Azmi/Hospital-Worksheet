const mongoose = require('mongoose');

const opdVisitDaySchema = new mongoose.Schema({
  
  OPDVisitId  : String,
  Date : String
  
}, {collection : 'OPDVisitDay'});

module.exports = mongoose.model('OPDVisitDay', opdVisitDaySchema, 'OPDVisitDay');

