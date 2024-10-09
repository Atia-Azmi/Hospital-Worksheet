const mongoose = require('mongoose');

const femalePatientSchema = new mongoose.Schema({
  
  PatientName : String
  
}, {collection : 'FemalePatient'});

module.exports = mongoose.model('FemalePatient', femalePatientSchema, 'FemalePatient');
