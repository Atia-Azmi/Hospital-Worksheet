const mongoose = require('mongoose');

const maternityPatientSchema = new mongoose.Schema({
  
  PatientName : String
  
}, {collection : 'MaternityPatient'});

module.exports = mongoose.model('MaternityPatient', maternityPatientSchema, 'MaternityPatient');
