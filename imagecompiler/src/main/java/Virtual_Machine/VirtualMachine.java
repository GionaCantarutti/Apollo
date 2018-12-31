package Virtual_Machine;

import ADTs.Adress;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class VirtualMachine implements VMInterface {

    Image image;
    Adress PC;
    Adress Pointer;
    Adress[] BGPointers;
    Boolean[] Flags;
    int XOffset;
    int YOffset;
    Boolean ERROR;
    int runningStatus;
    int RGBOffset;


    public VirtualMachine() {

        image = null;
        initializeInstance();

    }

    public VirtualMachine(Image image) {

        this.image = image;
        initializeInstance();

    }

    void initializeInstance() {

        PC = new Adress();
        Pointer = new Adress();
        BGPointers = new Adress[256];
        for (int i = 0; i < BGPointers.length; i++) {
            BGPointers[i] = new Adress();
        }
        Flags = new Boolean[256];
        XOffset = 0;
        YOffset = 0;
        ERROR = false;
        runningStatus = 0;
        RGBOffset = 0;

    }

//ToDo
    public void step() {



    }

    public Image getCurrentImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Adress getPC() {
        return PC;
    }

    //ToDo
    public Color getPCColor() {
        return null;
    }

    public Adress getPointer() {
        return Pointer;
    }

    //ToDo
    public Color getPointerColor() {
        return null;
    }

    public Adress[] getBackgroundPointers() {
        return new Adress[0];
    }

    public Boolean[] getFlags() {
        return Flags;
    }

    public int getXOffset() {
        return XOffset;
    }

    public int getYOffset() {
        return YOffset;
    }

    public void reset() {
        this.image = null;
        initializeInstance();
    }

    public boolean getERROR() {
        return ERROR;
    }

    public boolean getRunningStatus() {
        if (runningStatus == 2) {
            return false;
        } else return true;
    }
}
