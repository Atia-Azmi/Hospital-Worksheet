const ChildrenPatientVisit = require('../models/ChildrenPateintVisit');

exports.addChildrenPatientVisit = async(req, res)=>{
  
  try {
    
    const childrenPatientVisit = new ChildrenPatientVisit(req.body);
    
    await childrenPatientVisit.save();
    
    res.status(201).send(childrenPatientVisit);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(500).send(error);
    
  }
  
};
