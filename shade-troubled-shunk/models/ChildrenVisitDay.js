const mongoose = require('mongoose');

const childrenVisitDaySchema = new mongoose.Schema({
  
  ChildrenVisitId : String,
  Date : String
  
}, {collection : 'ChildrenVisitDay'});

module.exports = mongoose.model('ChildrenVisitDay', childrenVisitDaySchema, 'ChildrenVisitDay');

