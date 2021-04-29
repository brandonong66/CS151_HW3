package RGBpicker.controller;

import RGBpicker.model.RGBModel;
import RGBpicker.view.RGBView;

import java.util.concurrent.BlockingQueue;

public class RGBController {
    BlockingQueue<Message> queue;
    RGBModel model;
    RGBView view;

    public RGBController(BlockingQueue<Message> queue, RGBModel model, RGBView view) {
        this.queue = queue;
        this.model = model;
        this.view = view;
    }

    public void mainLoop() {
        while (view.isDisplayable()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException e) {
                //do nothing
            }

            if(message.getClass()==UpdateMessage.class)
            {
                //update button was clicked
                UpdateMessage updateMessage = (UpdateMessage) message;
                model.setRed(updateMessage.getRed());
                model.setGreen(updateMessage.getGreen());
                model.setBlue(updateMessage.getBlue());
                view.updateView();
            }
            else if(message.getClass()==ResetMessage.class)
            {
                //reset button was clicked
                ResetMessage resetMessage = (ResetMessage) message;
                model.reset();
                view.updateView();
            }
        }

    }
}
