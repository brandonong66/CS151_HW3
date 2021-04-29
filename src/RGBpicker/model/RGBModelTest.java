package RGBpicker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RGBModelTest {

    @Test
    void reset() {
        RGBModel testModel = new RGBModel();
        testModel.setRed(10);
        testModel.reset();
        assertEquals(0,testModel.getRed());

    }
}