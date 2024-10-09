package com.mycompany.myapp.Controller;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.myapp.View.AddNewVisitPage;

public class PreviousVisitAddController {

    Button previousVisitAdd;

    public String selectedDate;

    public String selectedWard;

    public Form form;

    ComboBox wards;

    Label leftDate, rightDate;

    public boolean left = false, right = false;
    
    public PreviousVisitAddController(Button previousVisitAdd) {
        this.previousVisitAdd = previousVisitAdd;
    }

    public void prepareButton() {

        previousVisitAdd.addActionListener(e -> {

            AddNewVisitPage addNewVisitPage = new AddNewVisitPage();

            selectedDate = left ? leftDate.getText() : rightDate.getText();
            
            addNewVisitPage.selectedDate = selectedDate;
            addNewVisitPage.previousForm = form;
            addNewVisitPage.selectedWard = wards.getSelectedItem().toString();

            addNewVisitPage.start();

        });

    }

    public Label getLeftDate() {
        return leftDate;
    }

    public void setLeftDate(Label leftDate) {
        this.leftDate = leftDate;
    }

    public Label getRightDate() {
        return rightDate;
    }

    public void setRightDate(Label rightDate) {
        this.rightDate = rightDate;
    }

    public Button getPreviousVisitAdd() {
        return previousVisitAdd;
    }

    public void setPreviousVisitAdd(Button previousVisitAdd) {
        this.previousVisitAdd = previousVisitAdd;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public ComboBox getWards() {
        return wards;
    }

    public void setWards(ComboBox wards) {
        this.wards = wards;
    }

}
