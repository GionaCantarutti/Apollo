package Virtual_Machine;

import javafx.scene.image.Image;
import ADTs.Adress;
import javafx.scene.paint.Color;

public interface VMInterface {

    /**
     * Steps the execution of the virtual machines.
     * Throws InvalidImageException
     */
    void step() throws InvalidImageException;

    Image getCurrentImage();

    void setImage();

    Adress getPC();

    Color getPCColor();

    Adress getPointer();

    Color getPointerColor();

    Adress[] getBackgroundPointers();

    Boolean[] getFlags();

    int getXOffset();

    int getYOffset();

    void reset();

}
