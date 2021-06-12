package com.goit.buttons;

import java.util.ArrayList;
import java.util.List;

public class Buttons {

    public static List<MyButton> nextButton(){
        List<MyButton> buttons = new ArrayList<>();
        buttons.add(new MyButton("Далее","Далее"));
        buttons.add(new MyButton("Настройки","Настройки"));
        return buttons;
    }
}
