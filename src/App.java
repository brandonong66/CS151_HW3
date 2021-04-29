import RGBpicker.controller.Message;
import RGBpicker.controller.RGBController;
import RGBpicker.model.RGBModel;
import RGBpicker.view.RGBView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public static void main(String[] args){

        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

        RGBModel model = new RGBModel();
        RGBView view = new RGBView(queue, model);
        RGBController controller = new RGBController(queue, model, view);
        controller.mainLoop();
    }
}
