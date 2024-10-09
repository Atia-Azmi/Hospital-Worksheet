const opdPatient = require('../models/OPDPatient');

exports.readOPDPtient = async(req, res)=>{
  
  try {
    
    const opdPatientData = await opdPatient.find();
    
    console.log('successfully find opd pateint data :- ', opdPatientData);
    
    res.status(201).send(opdPatientData);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(404).send(error);
    
  }
  
};
