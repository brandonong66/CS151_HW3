package RGBpicker.model;

public class RGBModel {
    private int red, green, blue;

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void reset() {
        red = 0;
        green = 0;
        blue = 0;
    }
}
