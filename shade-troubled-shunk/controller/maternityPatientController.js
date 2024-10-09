const maternityPatient = require('../models/MaternityPateint');

exports.readAllMaternityPatient = async(req, res)=>{
  
  try {
    
    const maternityPatientData = await maternityPatient.find();
    
    console.log('successfully read all the maternity patient :- ', maternityPatientData);
    
    res.status(201).send(maternityPatientData);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(404).send(error);
    
  }
  
};
