const FemaleVisitDay = require('../models/FemaleVisitDay');
const FemaleVisit = require('../models/FemalePatientVisit');
const FemalePatient = require('../models/FemalePatient');

exports.addFemaleVisitDay = async(req, res)=>{
  
  try {
    
    const femaleVisitDay = new FemaleVisitDay(req.body);
    
    await femaleVisitDay.save();
    
    res.status(201).send(femaleVisitDay);
    
    console.log('successfully added data :- ', femaleVisitDay);
    
  } catch(error) {
    
    console.log('error :- ', error);
    
    res.status(500).send(error);
    
  }
  
};

const parseTimeString = (timeString) => {
  const [hours, minutes] = timeString.split(':');
  const now = new Date(); 
  now.setHours(parseInt(hours), parseInt(minutes), 0, 0);
  return now;
};


const calculateVisitTime = (entryTime, exitTime) => {
  const entry = parseTimeString(entryTime); 
  const exit = parseTimeString(exitTime); 

  const diffMs = exit - entry; 
  const diffMinutes = Math.floor(diffMs / (1000 * 60)); 
  const hours = Math.floor(diffMinutes / 60);
  const minutes = diffMinutes % 60;

  return `${hours} hour(s) and ${minutes} minute(s)`;
};

exports.getVisitDataByDate = async (req, res) => {
  const { Date: date } = req.query;

  
  const dateString = date; 

  try {
    
    const visitDays = await FemaleVisitDay.find({ Date: dateString }).populate('FemaleVisitId');

    if (visitDays.length === 0) {
      return res.status(404).send({ message: 'No visits found for this date.' });
    }

    const result = await Promise.all(
      visitDays.map(async (visitDay) => {
        const FemaleVisitId = visitDay.FemaleVisitId;
        
        console.log('FemaleVisitId :- ', FemaleVisitId);
        
        const visit = await FemaleVisit.findById(FemaleVisitId);

        if (!visit) {
          return { message: `Visit not found for FemaleVisitId: ${FemaleVisitId}` };
        }

        const patient = await FemalePatient.findById(visit.patientId);

        if (!patient) {
          return { message: `Patient not found for PatientId: ${visit.patientId}` };
        }

       
        const entryTime = (visit.entryDate);
        const exitTime = (visit.exitDate);
        
        console.log('entryDate :- ', visit.entryDate, ' exit time :- ', visit.exitDate);

        return {
          childrenVisitId: FemaleVisitId,
          entryDate: visit.entryDate,
          exitDate: visit.exitDate,
          duration: calculateVisitTime(entryTime, exitTime),
          patientId: visit.patientId,
          patientName: patient.PatientName,
          _id : visitDay._id
        };
      })
    );

    res.status(200).send(result);

  } catch (error) {
    console.log('Error: ', error);
    res.status(500).send(error);
  }
};

exports.deleteChildVisitDay = async (req, res) => {
  const { id } = req.body; 

  try {
   
    const visitDay = await FemaleVisitDay.findById(id);
    if (!visitDay) {
      return res.status(404).send({ message: 'FemaleVisitDay not found' });
    }

    
    const childrenVisitId = visitDay.FemaleVisitId;

    
    await FemaleVisitDay.findByIdAndDelete(id);

    
    const visit = await FemaleVisit.findByIdAndDelete(childrenVisitId);
    if (!visit) {
      return res.status(404).send({ message: 'Associated ChildrenVisit not found' });
    }

    res.status(200).send({ message: 'Visit and VisitDay deleted successfully' });
  } catch (error) {
    console.log('Error: ', error);
    res.status(500).send({ message: 'Error deleting visit or visit day', error });
  }
};

