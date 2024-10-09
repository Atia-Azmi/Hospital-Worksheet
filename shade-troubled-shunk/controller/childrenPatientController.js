const childrenPatient = require('../models/ChildrenPateint');

exports.getAllChildrenPatient = async(req, res)=>{
  
  try {
    
    const childrenPatientData = await childrenPatient.find();
    
    res.status(201).send(childrenPatientData);
    
    console.log('successfully find the data :- ', childrenPatientData);
    
  } catch(error) {
    
    res.status(404).send(error);
    
    console.log('error :- ', error);
    
  }
  
};
