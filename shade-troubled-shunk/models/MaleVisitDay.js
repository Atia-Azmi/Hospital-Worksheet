const mongoose = require('mongoose');

const maleVisitDaySchema = new mongoose.Schema({
  
  MaleVisitId : String,
  Date : String
  
}, {collection : 'MaleVisitDay'});

module.exports = mongoose.model('MaleVisitDay', maleVisitDaySchema, 'MaleVisitDay');

