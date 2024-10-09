const OPDVisit = require('../models/OPDPatientVisit');

exports.addOPDPatientVisit = async(req, res)=>{
  
  try {
    
    const opdVisitPatientData = new OPDVisit(req.body);
    
    await opdVisitPatientData.save();
    
    res.status(201).send(opdVisitPatientData);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(500).send(error);
    
  }
  
};

