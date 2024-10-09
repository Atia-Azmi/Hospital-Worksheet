const MaternityPatientVisit = require('../models/MaternityPatientVisit');

exports.addMaternityPatientVisit = async(req, res)=>{
  
  try {
    
    const maternityPatientVisit = new MaternityPatientVisit(req.body);
    
    await maternityPatientVisit.save();
    
    res.status(201).send(maternityPatientVisit);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(500).send(error);
    
  }
  
};
