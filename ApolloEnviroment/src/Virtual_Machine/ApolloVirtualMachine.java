package Virtual_Machine;

import ADTs.Adress.Adress;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface ApolloVirtualMachine {

    /**
     * Steps the execution of the virtual machines.
     * Throws InvalidImageException
     */
    void step() throws InvalidImageException, MachineStoppedException;

    Image getCurrentImage();

    String getVersion();

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

    void runColor(Color c);

}
