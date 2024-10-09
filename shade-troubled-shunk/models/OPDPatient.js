const mongoose = require('mongoose');

const opdPatientSchema = new mongoose.Schema({
  
  PatientName : String
  
}, {collection : 'OPDPatient'});

module.exports = mongoose.model('OPDPatient', opdPatientSchema, 'OPDPatient');
