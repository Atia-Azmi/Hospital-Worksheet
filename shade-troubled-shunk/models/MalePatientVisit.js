const mongoose = require('mongoose');

const malePatientSchema = new mongoose.Schema({
  
  patientId : String,
  entryDate : String,
  exitDate : String
  
}, {collection : 'MaleVisit'});

module.exports = mongoose.model('MaleVisit', malePatientSchema, 'MaleVisit');

