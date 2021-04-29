package RGBpicker.controller;

import RGBpicker.controller.Message;

public class UpdateMessage implements Message {
    private int red, green, blue;
    public UpdateMessage(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public int getRed(){
        return red;
    }
    public int getGreen(){
        return green;
    }
    public int getBlue(){
        return blue;
    }
}
