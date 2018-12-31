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
    int Xoffset;
    int Yoffset;


    public VirtualMachine() {

        image = null;
        PC = new Adress();
        Pointer = new Adress();
        BGPointers = new Adress[256];
        for (int i = 0; i < BGPointers.length; i++) {
            BGPointers[i] = new Adress();
        }
        Flags = new Boolean[256];
        Xoffset = 0;
        Yoffset = 0;

    }

    public VirtualMachine(Image image) {

        this.image = image;
        PC = new Adress();
        Pointer = new Adress();
        BGPointers = new Adress[256];
        for (int i = 0; i < BGPointers.length; i++) {
            BGPointers[i] = new Adress();
        }
        Flags = new Boolean[256];
        Xoffset = 0;
        Yoffset = 0;

    }


    public void step() {

    }

    public Image getCurrentImage() {
        return null;
    }

    public void setImage() {

    }

    public Adress getPC() {
        return null;
    }

    public Color getPCColor() {
        return null;
    }

    public Adress getPointer() {
        return null;
    }

    public Color getPointerColor() {
        return null;
    }

    public Adress[] getBackgroundPointers() {
        return new Adress[0];
    }

    public Boolean[] getFlags() {
        return new Boolean[0];
    }

    public int getXOffset() {
        return 0;
    }

    public int getYOffset() {
        return 0;
    }

    public void reset() {

    }
}
