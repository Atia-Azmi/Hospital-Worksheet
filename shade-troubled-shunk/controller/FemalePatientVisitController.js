const FemalePatientVisit = require('../models/FemalePatientVisit');

exports.addFemalePatientVisit = async(req, res)=>{
  
  try {
    
    const femalePatientVisit = new FemalePatientVisit(req.body);
    
    await femalePatientVisit.save();
    
    res.status(201).send(femalePatientVisit);
    
  } catch(error) {
    
    console.log(error);
    
    res.status(500).send(error);
    
  }
  
};
