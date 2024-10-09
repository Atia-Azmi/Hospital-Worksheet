const mongoose = require('mongoose');

const malePatientSchema = new mongoose.Schema({
  
  PatientName : String
  
}, {collection : 'MalePatient'});

module.exports = mongoose.model('MalePatient', malePatientSchema, 'MalePatient');
