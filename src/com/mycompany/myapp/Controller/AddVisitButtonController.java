package com.mycompany.myapp.Controller;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.spinner.Picker;
import com.codename1.util.Callback;
import com.mycompany.myapp.Components.Colors;
import com.mycompany.myapp.Components.CustomFont;
import com.mycompany.myapp.Components.PatientList;
import com.mycompany.myapp.Model.PatientInformation;
import com.mycompany.myapp.Model.VisitInformation;
import com.mycompany.myapp.Reposatory.VisitDatabaseHelper;

public class AddVisitButtonController implements CustomFont, Colors {

    Button add = new Button("Add Visit");

    VisitInformation visit;

    public String selectedWard, selectedDate;

    VisitDatabaseHelper database = new VisitDatabaseHelper();

    Picker entryTimePicker, exitTimePicker;

    ComboBox<String> patients;

    String patientId;

    PatientList list;

    public void setAddVisitButton() {

        add.getAllStyles().setFont(font);
        add.getAllStyles().setBgColor(GLASS_BLUE);
        add.getAllStyles().setFgColor(GLASS_BLACK);
        add.getAllStyles().setBgTransparency(255);

        add.getAllStyles().setMargin(Component.TOP, 5);
        add.getAllStyles().setMargin(Component.BOTTOM, 5);
        add.getAllStyles().setPadding(Component.LEFT, 5);
        add.getAllStyles().setPadding(Component.RIGHT, 5);

        add.addActionListener(e -> {

            patientId = list.get(patients.getSelectedIndex()).get("_id").toString();

            int entryTimeInMinutes = convertTimeToMinutes(entryTimePicker.getText());
            int exitTimeInMinutes = convertTimeToMinutes(exitTimePicker.getText());

            if (entryTimeInMinutes >= exitTimeInMinutes) {
                Dialog.show("Invalid Time", "Entry time must be before exit time.", "OK", null);
                return;
            }

            visit = new VisitInformation(selectedWard, patientId, entryTimePicker.getText(), exitTimePicker.getText());

            Callback callback = new Callback() {
                @Override
                public void onSucess(Object value) {

                    Dialog.show("Database Info", value.toString(), "OK", "cancel");

                }

                @Override
                public void onError(Object sender, Throwable err, int errorCode, String errorMessage) {

                    Dialog.show("Database info", errorCode + " " + errorMessage, "OK", "cancel");

                }
            };

            database.setDate(selectedDate);
            database.addVisitInfo(visit, callback);

        });

    }

    public PatientList getList() {
        return list;
    }

    public void setList(PatientList list) {
        this.list = list;
    }

    public Button getAdd() {
        return add;
    }

    public VisitInformation getVisit() {
        return visit;
    }

    public void setVisit(VisitInformation visit) {
        this.visit = visit;
    }

    public String getSelectedWard() {
        return selectedWard;
    }

    public void setSelectedWard(String selectedWard) {
        this.selectedWard = selectedWard;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Picker getEntryTimePicker() {
        return entryTimePicker;
    }

    public void setEntryTimePicker(Picker entryTimePicker) {
        this.entryTimePicker = entryTimePicker;
    }

    public Picker getExitTimePicker() {
        return exitTimePicker;
    }

    public void setExitTimePicker(Picker exitTimePicker) {
        this.exitTimePicker = exitTimePicker;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public ComboBox<String> getPatients() {
        return patients;
    }

    public void setPatients(ComboBox<String> patients) {
        this.patients = patients;
    }

    private int convertTimeToMinutes(String time) {

        int colonIndex = time.indexOf(':');

        String hoursStr = time.substring(0, colonIndex);
        String minutesStr = time.substring(colonIndex + 1);

        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        return hours * 60 + minutes;
    }

}
