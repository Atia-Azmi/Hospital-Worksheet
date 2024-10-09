const MalePatientVisit = require('../models/MalePatientVisit');

exports.addMalePatientVisit = async(req, res)=>{
  
  try {
    
    const malePatientVisit = new MalePatientVisit(req.body);
    
    await malePatientVisit.save();
    
    res.status(201).send(malePatientVisit);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(500).send(error);
    
  }
  
};
