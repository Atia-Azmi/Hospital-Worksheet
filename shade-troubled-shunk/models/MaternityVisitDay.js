const mongoose = require('mongoose');

const maternityVisitDaySchema = new mongoose.Schema({
  
  MaternityVisitId : String,
  Date : String
  
}, {collection : 'MaternityVisitDay'});

module.exports = mongoose.model('MaternityVisitDay', maternityVisitDaySchema, 'MaternityVisitDay');
