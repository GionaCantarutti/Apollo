package Observer;

import ADTs.Adress.Adress;
import javafx.scene.image.Image;

public interface Observer {

    public boolean isWithImage();

    public boolean isWithPC();

    public boolean isWithPointer();

    public boolean isWithPointers();

    public boolean isWithFlags();

    public boolean isWithOffset();

    public boolean isWithError();

    public boolean isWithStatus();

    public boolean isWithStandardOutput();

    void updateImage(Image workingImage);

    void updateStandardOutput(String standardOutput);

    void updatePointer(Adress pointer);

    void updatePointers(Adress[] pointers);

    void updateFlags(Boolean[] flags);

    void updatePC(Adress PC);

    void updateError(boolean ERROR);

    void updateOffset(int xOffset, int yOffset);

    void updateStatus(boolean runningStatus);


}
