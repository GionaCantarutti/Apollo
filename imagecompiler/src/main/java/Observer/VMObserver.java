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

    @Override
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


    @Override
    public void updateImage(Image workingImage) {
        image = workingImage;
    }

    @Override
    public void updateStandardOutput(String standardOutput) {
        this.standardOutput = standardOutput;
    }

    @Override
    public void updatePointer(Adress pointer) {
        this.Pointer = pointer;
    }

    @Override
    public void updatePointers(Adress[] pointers) {
        this.BGPointers = pointers;
    }

    @Override
    public void updateFlags(Boolean[] flags) {
        this.Flags = flags;
    }

    @Override
    public void updatePC(Adress PC) {
        this.PC = PC;
    }

    @Override
    public void updateError(boolean ERROR) {
        this.ERROR = ERROR;
    }

    @Override
    public void updateOffset(int xOffset, int yOffset) {
        this.XOffset = xOffset;
        this.YOffset = yOffset;
    }

    @Override
    public void updateStatus(boolean runningStatus) {
        this.runningStatus = runningStatus;
    }

    @Override
    public void withImage() {
        withImage = true;
    }

    @Override
    public void withStandardOutput() {
        withStandardOutput = true;
    }

    @Override
    public void withPointer() {
        withPointer = true;
    }

    @Override
    public void withPointers() {
        withPointers= true;
    }

    @Override
    public void withFlags() {
        withFlags = true;
    }

    @Override
    public void withPC() {
        withPC = true;
    }

    @Override
    public void withError() {
        withError = true;
    }

    @Override
    public void withOffset() {
        withOffset = true;
    }

    @Override
    public void withStatus() {
        withStatus = true;
    }
}
