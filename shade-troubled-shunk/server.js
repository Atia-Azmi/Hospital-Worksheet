const express = require('express');
const mongoose = require('mongoose');
const app = express();

app.use(express.json());

const url = 'mongodb+srv://arponamitroy012:jqaPUF3LNhjpAbR7@cluster0.js0bs.mongodb.net/Students?retryWrites=true&w=majority';

mongoose.set('strictQuery', true);

mongoose.connect(url/*, {useNewUrlParser : true, useUnifiedTopology : true}*/)
.then(()=>{console.log('Connected with mongodb database')})
.catch(error=>{console.log('Database is not connected due to this error ', error)});

const port = process.env.PORT || 3000;

const childrenPatient = require('./controller/childrenPatientController');
const femalePatient = require('./controller/femlalePatientHandler');
const malePatient = require('./controller/malePatientController');
const maternityPatient = require('./controller/maternityPatientController');
const opdPatient = require('./controller/opdPatientController');

const ChildrenPatientController = require('./controller/ChildrenPatientVisitController');
const FemalePatientController = require('./controller/FemalePatientVisitController');
const MalePatientController = require('./controller/MalePatientVisitController');
const MaternityPatientController = require('./controller/MaternityPatientVisitController');
const OPDPatientController = require('./controller/OPDPatientVisitController');

const ChildrenVisitDayController = require('./controller/ChildrenVisitDayController');
const FemaleVisitDayController = require('./controller/FemaleVisitDayController');
const MaleVisitDayController = require('./controller/MaleVisitDayController');
const MaternityVisitDayController = require('./controller/MaternityVisitController');
const OPDVisitDayController = require('./controller/OPDVisitDayController');

app.get('/readAllChildrenPatient', childrenPatient.getAllChildrenPatient);
app.get('/readAllMalePatient', malePatient.getAllMalePatient);
app.get('/readAllFemalePatient', femalePatient.readAllFemalePatient);
app.get('/readAllMaternityPatient', maternityPatient.readAllMaternityPatient);
app.get('/readAllOPDPatient', opdPatient.readOPDPtient);

app.post('/addChildrenPatientVisit', ChildrenPatientController.addChildrenPatientVisit);
app.post('/addFemalePatientVisit', FemalePatientController.addFemalePatientVisit);
app.post('/addMalePatientVisit', MalePatientController.addMalePatientVisit );
app.post('/addMaternityPatientVisit', MaternityPatientController.addMaternityPatientVisit);
app.post('/addOPDPatientVisit', OPDPatientController.addOPDPatientVisit);

app.post('/addChildrenVisitDay', ChildrenVisitDayController.addChildVisitDay);
app.post('/addFemaleVisitDay', FemaleVisitDayController.addFemaleVisitDay);
app.post('/addMaleVisitDay', MaleVisitDayController.addMaleVisitDay );
app.post('/addMaternityVisitDay', MaternityVisitDayController.addMaternityVistDay);
app.post('/addOPDVisitDay', OPDVisitDayController.addOPDVisitDay);

app.get('/childrenSearchByDate', ChildrenVisitDayController.getVisitDataByDate );
app.get('/femaleSearchByDate', FemaleVisitDayController.getVisitDataByDate );
app.get('/maleSearchByDate', MaleVisitDayController.getVisitDataByDate );
app.get('/maternitySearchByDate', MaternityVisitDayController.getVisitDataByDate );
app.get('/opdSearchByDate', OPDVisitDayController.getVisitDataByDate );

app.post('/deleteChildrenVisitDay', ChildrenVisitDayController.deleteChildVisitDay);
app.post('/deleteFemaleVisitDay', FemaleVisitDayController.deleteChildVisitDay);
app.post('/deleteMaleVisitDay', MaleVisitDayController.deleteChildVisitDay );
app.post('/deleteMaternityVisitDay', MaternityVisitDayController.deleteChildVisitDay);
app.post('/deleteOPDVisitDay', OPDVisitDayController.deleteChildVisitDay);


app.listen(port, ()=>{
  
  console.log('Server is running on port ', port);
  
});


