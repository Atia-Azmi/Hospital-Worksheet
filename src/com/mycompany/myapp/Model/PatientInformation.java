package com.mycompany.myapp.Model;

public class PatientInformation {

    private String wardName;

    public PatientInformation(String wardName) {

        this.wardName = wardName;
    }

    public PatientInformation() {
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

}
