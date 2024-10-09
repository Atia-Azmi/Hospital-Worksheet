const mongoose = require('mongoose');

const maternitySchema = new mongoose.Schema({
  
  patientId : String,
  entryDate : String,
  exitDate : String
  
}, {collection : 'MaternityVisit'});

module.exports = mongoose.model('MaternityVisit', maternitySchema, 'MaternityVisit');

