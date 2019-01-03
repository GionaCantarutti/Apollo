package TestColors;

import javafx.scene.paint.Color;

public class TestColor {

    public static void main(String args[]) {

        Color c = Color.rgb(255, 100, 255);
        assert (c.getRed() == 255 && c.getGreen() == 100);
        if (c.getGreen()*255 != 100) {
            System.out.println("Oh fuck! " + c.getGreen()*255);
        }

    }

}
