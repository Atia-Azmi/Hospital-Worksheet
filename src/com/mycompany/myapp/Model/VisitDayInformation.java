package com.mycompany.myapp.Model;

public class VisitDayInformation {

    private String visitId, Date;

    public VisitDayInformation(String visitId, String Date) {
        this.visitId = visitId;
        this.Date = Date;
    }

    public VisitDayInformation() {
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

}
