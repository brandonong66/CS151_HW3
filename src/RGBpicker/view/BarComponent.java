package RGBpicker.view;

import RGBpicker.model.RGBModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class BarComponent extends JComponent {

    private final int HEIGHT_SCALE = 20;
    private int colorValue;
    private Color color;
    private int x, y, maxHeight, width;

    public BarComponent(int x, int y, int colorValue, Color color, int width, int maxHeight) {
        this.color = color;
        this.colorValue = colorValue;
        this.x = x;
        this.y = y;
        this.width = width;
        this.maxHeight = maxHeight;
    }

    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, maxHeight);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, maxHeight - colorValue * HEIGHT_SCALE);
    }

    public void updateBarSize(int colorValue) {
        this.colorValue = colorValue;
        repaint();
    }
}
