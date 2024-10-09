const malePatient = require('../models/MalePateint');

exports.getAllMalePatient = async(req, res)=>{
  
  try {
    
    const malePatientData = await malePatient.find();
    
    console.log('all the male patient data :- ', malePatientData);
    
    res.status(201).send(malePatientData);
    
  } catch(error) {
    
    res.status(404).send(error);
    
    console.log('error :- ', error);
    
  }
  
};
