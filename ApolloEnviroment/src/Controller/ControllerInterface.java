package Controller;

import ADTs.Adress;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface ControllerInterface {

    void step();

    void run();

    void stop();

    void setImage(Image image);

    void reset();

    Image getWorkingImage();

    Image getSourceImage();

    Adress getPC();

    Adress getPointer();

    boolean getERROR();

    void runColor(Color c);

    void terminate();

}
