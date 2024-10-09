package com.mycompany.myapp.View;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.getCurrentForm;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.Components.Colors;
import com.mycompany.myapp.Components.CustomDatePicker;
import com.mycompany.myapp.Components.CustomFont;
import com.mycompany.myapp.Components.PatientList;
import com.mycompany.myapp.Controller.AddVisitButtonController;
import com.mycompany.myapp.Model.PatientInformation;
import com.mycompany.myapp.Reposatory.PatientLoaderDatabaseHelper;

public class AddNewVisitPage extends CustomDatePicker implements CustomFont, Colors {

    private Form current, form;
    public Form previousForm;
    Container firstPart, secondPart, thirdPart, secondLeft, secondMid, secondRight, secondMidLeft, secondMidRight, thirdPartLeft, thirdPartRight, thirdPartLeftLeft, thirdPartLeftCenter, thirdPartLeftRight, thirdPartRightLeft, thirdPartRightCenter, thirdPartRightRight;
    private Resources theme;

    ComboBox<String> patients = new ComboBox<>();
    Picker entryTimePicker, exitTimePicker;

    AddVisitButtonController addVisitButtonController = new AddVisitButtonController();

    public String selectedDate, selectedWard;

    Button addVisit;

    PatientList patientList = new PatientList();

    PatientLoaderDatabaseHelper pateintDatabaseHelper = new PatientLoaderDatabaseHelper();

    PatientInformation patient;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {

        if (current != null) {
            current.show();
            return;
        }

        System.out.println("I am at here.");
        
        form = new Form("Visit Platform", new BoxLayout(BoxLayout.Y_AXIS));

        Toolbar toolbar = new Toolbar();

        form.setToolBar(toolbar);

        toolbar.addCommandToLeftBar("Back", null, e -> {

            if (previousForm != null) {

                previousForm.show();

            }

        });

        patients.getAllStyles().setMargin(Component.TOP, 5);
        patients.getAllStyles().setMargin(Component.BOTTOM, 5);
        patients.getAllStyles().setPadding(Component.LEFT, 5);
        patients.getAllStyles().setPadding(Component.RIGHT, 5);

        try {

            patient = new PatientInformation(selectedWard);

            patientList.clear();
            
            pateintDatabaseHelper.loadPatients(patient, patientList, patients);

        } catch (Exception e) {

        }

        //pateintDatabaseHelper
        entryTimePicker = new Picker();

        entryTimePicker.getAllStyles().setMargin(Component.TOP, 5);
        entryTimePicker.getAllStyles().setMargin(Component.BOTTOM, 5);
        entryTimePicker.getAllStyles().setPadding(Component.LEFT, 5);
        entryTimePicker.getAllStyles().setPadding(Component.RIGHT, 5);

        entryTimePicker.setType(Display.PICKER_TYPE_TIME);
        entryTimePicker.setEnabled(true);

        exitTimePicker = new Picker();

        exitTimePicker.getAllStyles().setMargin(Component.TOP, 5);
        exitTimePicker.getAllStyles().setMargin(Component.BOTTOM, 5);
        exitTimePicker.getAllStyles().setPadding(Component.LEFT, 5);
        exitTimePicker.getAllStyles().setPadding(Component.RIGHT, 5);

        exitTimePicker.setType(Display.PICKER_TYPE_TIME);
        exitTimePicker.setEnabled(true);

        addVisit = addVisitButtonController.getAdd();

        addVisitButtonController.setEntryTimePicker(entryTimePicker);
        addVisitButtonController.setExitTimePicker(exitTimePicker);
        addVisitButtonController.setPatients(patients);
        addVisitButtonController.setList(patientList);
        addVisitButtonController.selectedWard = selectedWard;
        addVisitButtonController.selectedDate = selectedDate;

        addVisitButtonController.setAddVisitButton();

        form.add(patients)
                .add(entryTimePicker)
                .add(exitTimePicker)
                .add(addVisit);

        form.setScrollable(true);

        form.revalidate();
        
        form.show();

    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
