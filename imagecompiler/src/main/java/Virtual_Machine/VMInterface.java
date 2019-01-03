package Virtual_Machine;

import javafx.scene.image.Image;
import ADTs.Adress;
import javafx.scene.paint.Color;

public interface VMInterface {

    /**
     * Steps the execution of the virtual machines.
     * Throws InvalidImageException
     */
    void step() throws InvalidImageException, MachineStoppedException;

    Image getCurrentImage();

    void setImage(Image image);

    Adress getPC();

    Color getPCColor();

    Adress getPointer();

    Color getPointerColor();

    Adress[] getBackgroundPointers();

    Boolean[] getFlags();

    int getXOffset();

    int getYOffset();

    void reset();

    void resetRunningStatus();

    boolean getERROR();

    boolean getRunningStatus();

}
