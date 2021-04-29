package RGBpicker.view;

import RGBpicker.controller.Message;
import RGBpicker.controller.ResetMessage;
import RGBpicker.controller.UpdateMessage;
import RGBpicker.model.RGBModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class RGBView extends JFrame {
    private final int WINDOW_HEIGHT = 300;
    private final int WINDOW_WIDTH = 500;

    private final int CONTROLS_PANEL_WIDTH = WINDOW_WIDTH / 3;
    private final int CONTROLS_PANEL_HEIGHT = WINDOW_HEIGHT / 3;
    private final int GRAPH_PANEL_WIDTH = 2 * WINDOW_WIDTH / 3;
    private final int GRAPH_PANEL_HEIGHT = 2 * WINDOW_HEIGHT / 3;

    private final int BAR_WIDTH = (int) (0.8 * GRAPH_PANEL_WIDTH) / 3;
    private final int BAR_MAX_HEIGHT = 2 * WINDOW_HEIGHT / 3;

    BlockingQueue<Message> queue;
    JTextField redTextField;
    JTextField greenTextField;
    JTextField blueTextField;

    JButton updateButton, resetButton;

    JLabel redLabel, blueLabel, greenLabel;
    BarComponent redBarComponent;
    BarComponent greenBarComponent;
    BarComponent blueBarComponent;

    RGBModel model;

    public RGBView(BlockingQueue<Message> queue, RGBModel model) {
        this.model = model;
        this.queue = queue;
        this.redLabel = new JLabel("Red:");
        this.greenLabel = new JLabel("Green:");
        this.blueLabel = new JLabel("Blue:");

        this.updateButton = new JButton("Update");
        this.resetButton = new JButton("Reset");

        this.redTextField = new JTextField(5);
        this.greenTextField = new JTextField(5);
        this.blueTextField = new JTextField(5);

        redBarComponent = new BarComponent(0, 0, model.getRed(), Color.RED, (int) (BAR_WIDTH*0.8), BAR_MAX_HEIGHT);
        greenBarComponent = new BarComponent(0, 0, model.getGreen(), Color.GREEN,(int) (BAR_WIDTH*0.8), BAR_MAX_HEIGHT);
        blueBarComponent = new BarComponent(0, 0, model.getBlue(), Color.BLUE, (int) (BAR_WIDTH*0.8), BAR_MAX_HEIGHT);

        //layout for entire window
        this.setLayout(new BorderLayout());
        this.setResizable(false);


        updateButton.addActionListener(e -> {
            try {
                int redValue = Integer.parseInt(redTextField.getText());
                int greenValue = Integer.parseInt(greenTextField.getText());
                int blueValue = Integer.parseInt(blueTextField.getText());

                Message msg = new UpdateMessage(redValue, greenValue, blueValue);
                queue.put(msg);
            } catch (InterruptedException interruptedException) {
                //do nothing
            } catch (Exception exception) {
                System.out.println("Invalid color value entered");
            }
        });

        resetButton.addActionListener(e -> {
            try {
                queue.put(new ResetMessage());
                redTextField.setText("");
                greenTextField.setText("");
                blueTextField.setText("");
            } catch (InterruptedException interruptedException) {

                //interruptedException.printStackTrace();
            }
        });


        //CONTROLS PANEL
        {
            JPanel controlsPanel = new JPanel();
            controlsPanel.setPreferredSize(new Dimension(CONTROLS_PANEL_WIDTH, CONTROLS_PANEL_HEIGHT));
            controlsPanel.setBackground(Color.LIGHT_GRAY);
            controlsPanel.setLayout(new GridBagLayout());
            this.add(controlsPanel, BorderLayout.WEST);
            GridBagConstraints gc = new GridBagConstraints();


            gc.weighty = 1;
            gc.weightx = 1;

            gc.anchor = GridBagConstraints.CENTER;
            gc.gridx = 0;
            gc.gridy = 0;
            controlsPanel.add(redLabel, gc);
            gc.gridy = 1;
            controlsPanel.add(redTextField, gc);

            gc.gridy = 2;
            controlsPanel.add(greenLabel, gc);
            gc.gridy = 3;
            controlsPanel.add(greenTextField, gc);

            gc.gridy = 4;
            controlsPanel.add(blueLabel, gc);
            gc.gridy = 5;
            controlsPanel.add(blueTextField, gc);

            gc.gridy = 6;
            controlsPanel.add(updateButton, gc);
            gc.gridy = 7;
            controlsPanel.add(resetButton, gc);
        }

        //BAR GRAPH PANEL
        {
            JPanel barGraphPanel = new JPanel();
            barGraphPanel.setPreferredSize(new Dimension(GRAPH_PANEL_WIDTH, GRAPH_PANEL_HEIGHT));
            barGraphPanel.setLayout(new GridBagLayout());
            this.add(barGraphPanel);
            barGraphPanel.setVisible(true);
            GridBagConstraints gc = new GridBagConstraints();
            barGraphPanel.setBackground(Color.LIGHT_GRAY);

            gc.gridy = 0;
            gc.gridx = 0;
            redBarComponent.setPreferredSize(new Dimension(BAR_WIDTH, BAR_MAX_HEIGHT));
            barGraphPanel.add(redBarComponent, gc);

            gc.gridx = 1;
            greenBarComponent.setPreferredSize(new Dimension(BAR_WIDTH, BAR_MAX_HEIGHT));
            barGraphPanel.add(greenBarComponent, gc);

            gc.gridx = 2;
            blueBarComponent.setPreferredSize(new Dimension(BAR_WIDTH, BAR_MAX_HEIGHT));
            barGraphPanel.add(blueBarComponent, gc);

        }
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateView() {
        redBarComponent.updateBarSize(model.getRed());
        greenBarComponent.updateBarSize(model.getGreen());
        blueBarComponent.updateBarSize(model.getBlue());
    }
}
