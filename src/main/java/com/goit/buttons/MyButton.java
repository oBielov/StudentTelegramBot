package com.goit.buttons;

import lombok.Data;

@Data
public class MyButton {
    private String textButton;
    private String dataButton;

    public MyButton(String textButton,String dataButton) {
        this.dataButton = dataButton;
        this.textButton = textButton;
    }
}
