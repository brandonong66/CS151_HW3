package RGBpicker.controller;

public class ResetMessage implements Message{
    private int red, green, blue;
    public ResetMessage()
    {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
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
