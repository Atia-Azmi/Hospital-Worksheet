const femalePatient = require('../models/FemalePatient');

exports.readAllFemalePatient = async(req, res)=>{
  
  try {
    
    const femalePatientData = await femalePatient.find();
    
    res.status(201).send(femalePatientData);
    
    console.log('successfully read all the female patient data :- ', femalePatientData);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(404).send(error);
    
  }
  
};
