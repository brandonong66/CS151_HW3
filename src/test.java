import javax.swing.*;
import java.awt.*;

public class test {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("test button");
        JButton button2  = new JButton("button 2");

        final int FIELD_WIDTH = 10;
        JTextField textField = new JTextField(FIELD_WIDTH);

        //frame.pack();
        frame.setSize(500,500);
        frame.setLayout(new FlowLayout());
        frame.add(button1);
        frame.add(button2);
        frame.add(textField);
        button1.addActionListener(event ->textField.setText("button1 has been pressed"));
        button2.addActionListener(event ->textField.setText("button2 has been pressed"));

        frame.setVisible(true);
    }



}
