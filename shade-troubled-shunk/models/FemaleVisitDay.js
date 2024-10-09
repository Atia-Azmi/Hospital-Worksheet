const mongoose = require('mongoose');

const femaleVisitDaySchema = new mongoose.Schema({
  
  FemaleVisitId : String,
  Date : String
  
}, {collection : 'FemaleVisitDay'});

module.exports = mongoose.model('FemaleVisitDay', femaleVisitDaySchema, 'FemaleVisitDay');

