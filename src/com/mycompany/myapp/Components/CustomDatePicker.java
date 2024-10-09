package com.mycompany.myapp.Components;

import com.codename1.ui.Display;
import com.codename1.ui.spinner.Picker;

public abstract class CustomDatePicker {

    Picker datePicker;

    public Picker getDatePicker() {

        datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setEnabled(true);

        return datePicker;
    }

}
