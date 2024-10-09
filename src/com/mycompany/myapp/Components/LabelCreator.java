package com.mycompany.myapp.Components;

import com.codename1.ui.Label;

public class LabelCreator implements CustomFont {

    Label label;

    public LabelCreator(String title) {
        
        this.label = new Label(title);
        this.label.getAllStyles().setFont(font);
    
    }

    public Label getLabel() {
        return label;
    }

}
