const mongoose = require('mongoose');

const childrenPatientSchema = new mongoose.Schema({
  
  PatientName : String
  
}, {collection : 'ChildrenPatient'});

module.exports = mongoose.model('ChildrenPatient', childrenPatientSchema, 'ChildrenPatient');

