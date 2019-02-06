package Observer;

import ADTs.Adress;
import javafx.scene.image.Image;

public class VMObserver implements Observer{

    String standardOutput;

    Image image;

    Adress PC;
    Adress Pointer;
    Adress[] BGPointers;
    Boolean[] Flags;
    int XOffset;
    int YOffset;
    Boolean ERROR;

    public String getStandardOutput() {
        return standardOutput;
    }

    public Image getImage() {
        return image;
    }

    public Adress getPC() {
        return PC;
    }

    public Adress getPointer() {
        return Pointer;
    }

    public Adress[] getBGPointers() {
        return BGPointers;
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

    public Boolean getERROR() {
        return ERROR;
    }

    public boolean isRunningStatus() {
        return runningStatus;
    }

    boolean runningStatus;

    boolean withStandardOutput;
    boolean withImage;
    boolean withPC;
    boolean withPointer;

    public boolean isWithImage() {
        return withImage;
    }

    public boolean isWithPC() {
        return withPC;
    }

    public boolean isWithPointer() {
        return withPointer;
    }

    public boolean isWithPointers() {
        return withPointers;
    }

    public boolean isWithFlags() {
        return withFlags;
    }

    public boolean isWithOffset() {
        return withOffset;
    }

    public boolean isWithError() {
        return withError;
    }

    public boolean isWithStatus() {
        return withStatus;
    }

    public boolean isWithStandardOutput() {
        return withStandardOutput;
    }

    boolean withPointers;
    boolean withFlags;
    boolean withOffset;
    boolean withError;
    boolean withStatus;


    public VMObserver() {
        withStandardOutput = false;
        withImage = false;
        withPC = false;
        withPointer = false;
        withPointers = false;
        withFlags = false;
        withOffset = false;
        withError = false;
        withStatus = false;
    }


    public void updateImage(Image workingImage) {
        image = workingImage;
    }

    public void updateStandardOutput(String standardOutput) {
        this.standardOutput = standardOutput;
    }

    public void updatePointer(Adress pointer) {
        this.Pointer = pointer;
    }

    public void updatePointers(Adress[] pointers) {
        this.BGPointers = pointers;
    }

    public void updateFlags(Boolean[] flags) {
        this.Flags = flags;
    }

    public void updatePC(Adress PC) {
        this.PC = PC;
    }

    public void updateError(boolean ERROR) {
        this.ERROR = ERROR;
    }

    public void updateOffset(int xOffset, int yOffset) {
        this.XOffset = xOffset;
        this.YOffset = yOffset;
    }

    public void updateStatus(boolean runningStatus) {
        this.runningStatus = runningStatus;
    }

    public void withImage() {
        withImage = true;
    }

    public void withStandardOutput() {
        withStandardOutput = true;
    }

    public void withPointer() {
        withPointer = true;
    }

    public void withPointers() {
        withPointers= true;
    }

    public void withFlags() {
        withFlags = true;
    }

    public void withPC() {
        withPC = true;
    }

    public void withError() {
        withError = true;
    }

    public void withOffset() {
        withOffset = true;
    }

    public void withStatus() {
        withStatus = true;
    }
}
