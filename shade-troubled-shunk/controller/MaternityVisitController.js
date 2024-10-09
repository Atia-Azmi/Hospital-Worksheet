const MaternityVisitDay = require('../models/MaternityVisitDay');
const MaternitynVisit = require('../models/MaternityPatientVisit');
const MaternityPatient = require('../models/MaternityPateint');

exports.addMaternityVistDay = async(req, res)=>{
  
  try {
    
    const maternityVisit = new MaternityVisitDay(req.body);
    
    await maternityVisit.save();
    
    res.status(201).send(maternityVisit);
    
    console.log('successfully added data :- ', maternityVisit);
    
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
    
    const visitDays = await MaternityVisitDay.find({ Date: dateString }).populate('MaternityVisitId');

    if (visitDays.length === 0) {
      return res.status(404).send({ message: 'No visits found for this date.' });
    }

    const result = await Promise.all(
      visitDays.map(async (visitDay) => {
        const MaternityVisitId = visitDay.MaternityVisitId;
        
        console.log('MaternityVisitId :- ', MaternityVisitId);
        
        const visit = await MaternitynVisit.findById(MaternityVisitId);

        if (!visit) {
          return { message: `Visit not found for ChildrenVisitId: ${MaternityVisitId}` };
        }

        const patient = await MaternityPatient.findById(visit.patientId);

        if (!patient) {
          return { message: `Patient not found for PatientId: ${visit.patientId}` };
        }

       
        const entryTime = (visit.entryDate);
        const exitTime = (visit.exitDate);
        
        console.log('entryDate :- ', visit.entryDate, ' exit time :- ', visit.exitDate);

        return {
          childrenVisitId: MaternityVisitId,
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
  
  console.log('_id :- ', id);

  try {
   
    const visitDay = await MaternityVisitDay.findById(id );
    if (!visitDay) {
      return res.status(404).send({ message: 'MaternityVisitDay not found' });
    }

    
    const childrenVisitId = visitDay.MaternityVisitId;

    
    await MaternityVisitDay.findByIdAndDelete(id);

    
    const visit = await MaternitynVisit.findByIdAndDelete(childrenVisitId);
    if (!visit) {
      return res.status(404).send({ message: 'Associated ChildrenVisit not found' });
    }

    res.status(200).send({ message: 'Visit and VisitDay deleted successfully' });
  } catch (error) {
    console.log('Error: ', error);
    res.status(500).send({ message: 'Error deleting visit or visit day', error });
  }
};
