package com.mycompany.myapp.Model;

public class VisitInformation {

    private String wardName, patientId, entryTime, exitTime;

    public VisitInformation() {
    }

    public VisitInformation(String wardName, String patientId, String entryTime, String exitTime) {
        this.wardName = wardName;
        this.patientId = patientId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

}
