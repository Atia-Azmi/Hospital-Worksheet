const mongoose = require('mongoose');

const childrenPatientSchema = new mongoose.Schema({
  
  patientId : String,
  entryDate : String,
  exitDate : String
  
}, {collection : 'ChildrenVisit'});

module.exports = mongoose.model('ChildrenVisit', childrenPatientSchema, 'ChildrenVisit');
