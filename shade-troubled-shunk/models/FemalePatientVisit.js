const mongoose = require('mongoose');

const femalePatientSchema = new mongoose.Schema({
  
  patientId : String,
  entryDate : String,
  exitDate : String
  
}, {collection : 'FemaleVisit'});

module.exports = mongoose.model('FemaleVisit', femalePatientSchema, 'FemaleVisit');
