const mongoose = require('mongoose');

const opdPatientSchema = mongoose.Schema({
  
  patientId : String,
  entryDate : String,
  exitDate : String
  
}, {collection : 'OPDVisit'});

module.exports = mongoose.model('OPDVisit', opdPatientSchema, 'OPDVisit');


